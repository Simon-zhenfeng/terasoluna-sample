package jp.terasoluna.batch.functionsample.b007.b007001;

import javax.inject.Inject;
import javax.inject.Named;

import jp.terasoluna.batch.functionsample.b007.CsvRecord;
import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.file.dao.FileQueryDAO;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ���̓f�[�^�擾�@�\���g�p�����t�@�C��-DB�֘A�W���u�̃T���v��<br>
 * <p>
 * ���O�����FC:\tmp�z����input.csv�t�@�C����z�u���邱�� <br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���A<br>
 * �t�@�C����ǂݍ��݁ADB�ɍX�V����T���v���B<br>
 * </p>
 */
@Component
public class B007001BLogic extends AbstractTransactionBLogic {

    private Logger log = LoggerFactory.getLogger(B007001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    private static final String INPUT_FILE = "C:\\tmp\\input.csv";

    @Inject
    B007001BatchDao dao;

    @Inject
    @Named("batchSqlSessionTemplate")
    SqlSession sqlSession;

    @Inject
    @Named("csvFileQueryDAO")
    FileQueryDAO csvFileQueryDAO;

    @Override
    public int doMain(BLogicParam arg0) {

        // EMPLOYEE�e�[�u���̏�����
        dao.deleteEmployees();

        Collector<CsvRecord> collector = new FileCollector<CsvRecord>(
                csvFileQueryDAO, INPUT_FILE, CsvRecord.class);

        try {

            int insertCount = 0;
            boolean isInfoEnabled = log.isInfoEnabled();

            while (collector.hasNext()) {
                CsvRecord record = collector.next();
                if (isInfoEnabled) {
                    log.info("ID:{} FAMILYNAME:{} FIRSTNAME:{} AGE:{}",
                            record.getId(), record.getFamilyName(),
                            record.getFirstName(), record.getAge());
                }

                dao.insertEmployee(record);
                insertCount++;

                // 10�����ƂɃo�b�`�X�V���s
                if (insertCount % 10 == 0) {
                    log.info("�o�b�`�X�V���s");
                    sqlSession.flushStatements();
                }

            }
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            // �R���N�^�̃N���[�Y
            CollectorUtility.closeQuietly(collector);
        }

        // ����I��
        return BATCH_NORMAL_END;
    }

}
