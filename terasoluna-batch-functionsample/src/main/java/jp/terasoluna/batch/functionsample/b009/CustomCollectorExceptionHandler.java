package jp.terasoluna.batch.functionsample.b009;

import jp.terasoluna.fw.collector.exception.CollectorExceptionHandler;
import jp.terasoluna.fw.collector.exception.CollectorExceptionHandlerStatus;
import jp.terasoluna.fw.collector.validate.ValidationErrorException;
import jp.terasoluna.fw.collector.vo.DataValueObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author btyamurat
 */
public class CustomCollectorExceptionHandler implements
                                            CollectorExceptionHandler {
    /**
     * ���O.<br>
     */
    private static Logger log = LoggerFactory
            .getLogger(CustomCollectorExceptionHandler.class);

    /**
     * ���̓`�F�b�N�G���[����.<br>
     */
    protected int errorFieldCount = 0;

    public CollectorExceptionHandlerStatus handleException(
            DataValueObject dataValueObject) {
        errorFieldCount++;

        if (log.isErrorEnabled()) {
            if (dataValueObject.getThrowable() instanceof ValidationErrorException) {
                log.error("ValidationErrorException is Thrown...");
            } else {
                log.error("Exception is Thrown...");
            }
        }

        return CollectorExceptionHandlerStatus.END;
    }

    /**
     * errorFieldCount���擾����B
     * @return errorFieldCount
     */
    public int getErrorFieldCount() {
        return errorFieldCount;
    }
}
