package jp.terasoluna.batch.functionsample.b007;

import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.file.dao.FileQueryDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private Log log = LogFactory.getLog(B007001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    private static final String INPUT_FILE = "C:\\tmp\\input.csv";

    @Autowired
    @Qualifier("csvFileQueryDAO")
    private FileQueryDAO csvFileQueryDAO = null;

    @Autowired
    @Qualifier("updateDAO")
    private UpdateDAO updateDAO = null;

    @Override
    public int doMain(BLogicParam arg0) {

        // EMPLOYEE�e�[�u���̏�����
        updateDAO.execute("B007001.deleteEmployees", null);

        Collector<CsvRecord> collector = new FileCollector<CsvRecord>(
                csvFileQueryDAO, INPUT_FILE, CsvRecord.class);

        try {

            while (collector.hasNext()) {
                CsvRecord record = collector.next();
                log.info("ID:" + record.getId() + " FIMILYNAME:"
                        + record.getFamilyName() + " FIRSTNAME:"
                        + record.getFamilyName() + " AGE:" + record.getAge());

                updateDAO.execute("B007001.insertEmployees", record);

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
