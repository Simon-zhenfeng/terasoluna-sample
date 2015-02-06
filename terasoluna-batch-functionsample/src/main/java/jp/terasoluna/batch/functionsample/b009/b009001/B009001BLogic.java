package jp.terasoluna.batch.functionsample.b009.b009001;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jp.terasoluna.batch.functionsample.b009.SkipValidationErrorHandler;
import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileValidateCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.file.dao.FileLineWriter;
import jp.terasoluna.fw.file.dao.FileQueryDAO;
import jp.terasoluna.fw.file.dao.FileUpdateDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * ���̓f�[�^�擾�@�\���g�p�����ۂ̓��̓`�F�b�N�@�\�̃T���v��<br>
 * <p>
 * ���O�����FC:\tmp�z����inputB009001.csv�t�@�C����z�u���邱��<br>
 * </p>
 * <p>
 * �T���v�����e�FinputB009001.csv�t�@�C����ǂݎ��AoutputB009001.csv���o�͂���B<br>
 * �g�����̓`�F�b�N�G���[�n���h�����g�p���A�X�e�[�^�X�ɂ�SKIP��ԋp����B<br>
 * ���̓`�F�b�N�G���[�����������f�[�^�ȊO���o�͂��邪�A�I���X�e�[�^�X��100���Z�b�g����B <br>
 * </p>
 */
@Component
public class B009001BLogic implements BLogic {

    private Logger log = LoggerFactory.getLogger(B009001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    private static final int BATCH_ABNORMAL_END = 100;

    private static final String INPUT_FILE = "C:\\tmp\\inputB009001.csv";

    private static final String OUTPUT_FILE = "C:\\tmp\\outputB009001.csv";

    @Inject
    @Named("csvFileQueryDAO")
    FileQueryDAO csvFileQueryDAO;

    @Inject
    @Named("csvFileUpdateDAO")
    FileUpdateDAO csvFileUpdateDAO;

    @Inject
    @Named("beanValidator")
    Validator beanValidator;

    public int execute(BLogicParam arg0) {

        int returnCode = BATCH_NORMAL_END;

        SkipValidationErrorHandler errorHandler = new SkipValidationErrorHandler();
        Collector<CsvRecord> collector = new FileValidateCollector<CsvRecord>(
                csvFileQueryDAO, INPUT_FILE, CsvRecord.class, beanValidator,
                errorHandler);

        FileLineWriter<CsvRecord> fileLineWriter = csvFileUpdateDAO.execute(
                OUTPUT_FILE, CsvRecord.class);

        List<String> header = new ArrayList<String>();
        header.add("header");
        List<String> footer = new ArrayList<String>();
        footer.add("footer");

        try {
            // �w�b�_���̏o��
            fileLineWriter.printHeaderLine(header);

            while (collector.hasNext()) {
                CsvRecord record = collector.next();
                if (log.isInfoEnabled()) {
                    log.info("ID:{} FAMILYNAME:{} FIRSTNAME:{} AGE:{}",
                            record.getId(), record.getFamilyName(),
                            record.getFirstName(), record.getAge());
                }

                // �f�[�^���̏o��
                fileLineWriter.printDataLine(record);
            }
            // �t�b�_���̏o��
            fileLineWriter.printTrailerLine(footer);
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            // �R���N�^�E�t�@�C���̃N���[�Y
            CollectorUtility.closeQuietly(collector);
            CollectorUtility.closeQuietly(fileLineWriter);
        }

        // �R���N�^�������ɔ����������̓`�F�b�N�G���[���擾
        if (errorHandler.getErrorFieldCount() > 0) {
            returnCode = BATCH_ABNORMAL_END;
            if (log.isInfoEnabled()) {
                Errors[] errors = errorHandler.getErrors();
                for (Errors error : errors) {
                    log.info(error.toString());
                }
            }
        }

        // ����I��
        return returnCode;
    }

}
