package jp.terasoluna.batch.functionsample.b009;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileValidateCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.file.dao.FileLineWriter;
import jp.terasoluna.fw.file.dao.FileQueryDAO;
import jp.terasoluna.fw.file.dao.FileUpdateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 入力データ取得機能を使用した際の入力チェック機能のサンプル<br>
 * <p>
 * 事前準備：C:\tmp配下にinputB009001.csvファイルを配置すること<br>
 * </p>
 * <p>
 * サンプル内容：inputB009001.csvファイルを読み取り、outputB009001.csvを出力する。<br>
 * 拡張入力チェックエラーハンドラを使用し、ステータスにはSKIPを返却する。<br>
 * 入力チェックエラーが発生したデータ以外を出力するが、終了ステータスは100をセットする。 <br>
 *</p>
 */
@Component
public class B009001BLogic implements BLogic {

    private Log log = LogFactory.getLog(B009001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    private static final int BATCH_ABNORMAL_END = 100;

    private static final String INPUT_FILE = "C:\\tmp\\inputB009001.csv";

    private static final String OUTPUT_FILE = "C:\\tmp\\outputB009001.csv";

    @Autowired
    @Qualifier("csvFileQueryDAO")
    private FileQueryDAO csvFileQueryDAO = null;

    @Autowired
    @Qualifier("csvFileUpdateDAO")
    private FileUpdateDAO csvFileUpdateDAO = null;

    @Autowired
    @Qualifier("beanValidator")
    private Validator beanValidator = null;

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
            // ヘッダ部の出力
            fileLineWriter.printHeaderLine(header);

            while (collector.hasNext()) {
                CsvRecord record = collector.next();
                if (log.isInfoEnabled()) {
                    log
                            .info("ID:" + record.getId() + " FIMILYNAME:"
                                    + record.getFamilyName() + " FIRSTNAME:"
                                    + record.getFirstName() + " AGE:"
                                    + record.getAge());
                }

                // データ部の出力
                fileLineWriter.printDataLine(record);
            }
            // フッダ部の出力
            fileLineWriter.printTrailerLine(footer);
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            // コレクタ・ファイルのクローズ
            CollectorUtility.closeQuietly(collector);
            CollectorUtility.closeQuietly(fileLineWriter);
        }

        // コレクタ処理中に発生した入力チェックエラーを取得
        if (errorHandler.getErrorFieldCount() > 0) {
            returnCode = BATCH_ABNORMAL_END;
            if (log.isInfoEnabled()) {
                Errors[] errors = errorHandler.getErrors();
                for (Errors error : errors) {
                    log.info(error.toString());
                }
            }
        }

        // 正常終了
        return returnCode;
    }

}
