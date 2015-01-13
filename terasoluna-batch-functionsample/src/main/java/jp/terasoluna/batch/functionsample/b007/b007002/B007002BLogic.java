package jp.terasoluna.batch.functionsample.b007.b007002;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jp.terasoluna.batch.functionsample.b007.CsvRecord;
import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.batch.util.BatchUtil;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.db.DBCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.file.dao.FileLineWriter;
import jp.terasoluna.fw.file.dao.FileUpdateDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * ���̓f�[�^�擾�@�\���g�p����DB-�t�@�C���֘A�W���u�̃T���v��<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u�����쐬���Ă�������<br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���A<br>
 * DB���Q�Ƃ��A�t�@�C��(C:\\tmp\\outputB007002.csv)�ɏ����o���T���v���B<br>
 * </p>
 */
@Component
public class B007002BLogic implements BLogic {

    private Logger log = LoggerFactory.getLogger(B007002BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    private static final String OUTPUT_FILE = "C:\\tmp\\outputB007002.csv";

    @Inject
    B007002Dao dao;

    @Inject
    @Named("csvFileUpdateDAO")
    FileUpdateDAO csvFileUpdateDAO;

    @Inject
    PlatformTransactionManager transactionManager;

    public int execute(BLogicParam arg0) {

        TransactionStatus stat = null;
        stat = BatchUtil.startTransaction(transactionManager);
        Collector<CsvRecord> collector = null;
        FileLineWriter<CsvRecord> fileLineWriter = null;

        try {
            collector = new DBCollector<CsvRecord>(dao, "collectEmployee", null);

            fileLineWriter = csvFileUpdateDAO.execute(OUTPUT_FILE,
                    CsvRecord.class);

            List<String> header = new ArrayList<String>();
            header.add("header");
            List<String> footer = new ArrayList<String>();
            footer.add("footer");

            // �w�b�_���̏o��
            fileLineWriter.printHeaderLine(header);

            for (CsvRecord data : collector) {

                log.info("ID:" + data.getId() + " FIMILYNAME:"
                        + data.getFamilyName() + " FIRSTNAME:"
                        + data.getFirstName() + " AGE:" + data.getAge());

                // �f�[�^���̏o��
                fileLineWriter.printDataLine(data);
            }

            // �t�b�_���̏o��
            fileLineWriter.printTrailerLine(footer);

            BatchUtil.commitTransaction(transactionManager, stat);
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            // �R���N�^�E�t�@�C���̃N���[�Y
            CollectorUtility.closeQuietly(collector);
            CollectorUtility.closeQuietly(fileLineWriter);
            BatchUtil.endTransaction(transactionManager, stat);
        }

        // ����I��
        return BATCH_NORMAL_END;
    }

}
