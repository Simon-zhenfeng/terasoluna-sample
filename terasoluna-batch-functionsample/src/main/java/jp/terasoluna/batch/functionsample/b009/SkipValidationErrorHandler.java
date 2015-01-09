/*
 * Copyright (c) 2011 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.terasoluna.batch.functionsample.b009;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import jp.terasoluna.fw.collector.validate.ValidateErrorStatus;
import jp.terasoluna.fw.collector.validate.ValidationErrorHandler;
import jp.terasoluna.fw.collector.vo.DataValueObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * ���̓`�F�b�N�G���[�n���h���̎����N���X.<br>
 * <p>
 * ���̓`�F�b�N�G���[���������ꍇ�́AINFO���O�ɃG���[�R�[�h���o�͂���B<br>
 * �߂�l�͕K��ValidateStatus.SKIP��Ԃ��B
 * </p>
 */
public class SkipValidationErrorHandler implements ValidationErrorHandler {
    /**
     * Log.
     */
    private static Logger logger = LoggerFactory
            .getLogger(SkipValidationErrorHandler.class);

    /** ���O���x��(TRACE) */
    public static final String LOG_LEVEL_TRACE = "trace";

    /** ���O���x��(DEBUG) */
    public static final String LOG_LEVEL_DEBUG = "debug";

    /** ���O���x��(INFO) */
    public static final String LOG_LEVEL_INFO = "info";

    /** ���O���x��(WARN) */
    public static final String LOG_LEVEL_WARN = "warn";

    /** ���O���x��(ERROR) */
    public static final String LOG_LEVEL_ERROR = "error";

    /** ���O���x��(FATAL) */
    public static final String LOG_LEVEL_FATAL = "fatal";

    /**
     * ���̓`�F�b�N�G���[���ɕԂ��X�e�[�^�X.<br>
     * <p>
     * �f�t�H���g�ł�ERROR_SKIP
     * </p>
     */
    protected ValidateErrorStatus validateStatus = ValidateErrorStatus.SKIP;

    /**
     * ���̓`�F�b�N�G���[����.<br>
     */
    protected int errorFieldCount = 0;

    /**
     * �G���[�L���[.<br>
     */
    protected BlockingQueue<Errors> errorsQueue = new LinkedBlockingQueue<Errors>();

    /**
     * ���O���x��.<br>
     */
    protected String logLevel = LOG_LEVEL_INFO;

    /**
     * �R���X�g���N�^.<br>
     */
    public SkipValidationErrorHandler() {
        super();
    }

    /**
     * �R���X�g���N�^.<br>
     * @param logLevel String ���O���x��
     */
    public SkipValidationErrorHandler(String logLevel) {
        this();
        this.logLevel = logLevel;
    }

    /**
     * �R���X�g���N�^.<br>
     * @param validateStatus ValidateStatus ���̓`�F�b�N�G���[���ɕԂ��X�e�[�^�X
     */
    public SkipValidationErrorHandler(ValidateErrorStatus validateStatus) {
        this();
        this.validateStatus = validateStatus;
    }

    /**
     * �R���X�g���N�^.<br>
     * @param validateStatus ValidateStatus ���̓`�F�b�N�G���[���ɕԂ��X�e�[�^�X
     * @param logLevel String ���O���x��
     */
    public SkipValidationErrorHandler(ValidateErrorStatus validateStatus,
            String logLevel) {
        this();
        this.validateStatus = validateStatus;
        this.logLevel = logLevel;
    }

    /*
     * (non-Javadoc)
     * @seejp.terasoluna.fw.ex.iterator.validate.ValidationErrorHandler#handleValidationError(jp.terasoluna.fw.ex.iterator.vo.
     * DataValueObject, org.springframework.validation.Errors)
     */
    public ValidateErrorStatus handleValidationError(
            DataValueObject dataValueObject, Errors errors) {
        errorFieldCount++;

        try {
            if (errors != null) {
                errorsQueue.put(errors);
            }
        } catch (InterruptedException e) {
            // ��������
        }

        // ���O�o��
        outputLog(dataValueObject, errors);

        // ValidateStatus��Ԃ�
        return getValidateStatus(dataValueObject, errors);
    }

    /**
     * ���O�o��
     * @param dataValueObject DataValueObject
     * @param errors Errors
     */
    protected void outputLog(DataValueObject dataValueObject, Errors errors) {
        if (LOG_LEVEL_TRACE.equalsIgnoreCase(this.logLevel)
                && logger.isTraceEnabled()) {
            logger.trace(logEdit(dataValueObject, errors));
        } else if (LOG_LEVEL_DEBUG.equalsIgnoreCase(this.logLevel)
                && logger.isDebugEnabled()) {
            logger.debug(logEdit(dataValueObject, errors));
        } else if (LOG_LEVEL_INFO.equalsIgnoreCase(this.logLevel)
                && logger.isInfoEnabled()) {
            logger.info(logEdit(dataValueObject, errors));
        } else if (LOG_LEVEL_WARN.equalsIgnoreCase(this.logLevel)
                && logger.isWarnEnabled()) {
            logger.warn(logEdit(dataValueObject, errors));
        } else if (LOG_LEVEL_ERROR.equalsIgnoreCase(this.logLevel)
                && logger.isErrorEnabled()) {
            logger.error(logEdit(dataValueObject, errors));
        } else if (logger.isTraceEnabled()) {
            logger.trace(logEdit(dataValueObject, errors));
        }
    }

    /**
     * ���O�ҏW.<br>
     * @param dataValueObject DataValueObject
     * @param errors Errors
     * @return ���O
     */
    protected String logEdit(DataValueObject dataValueObject, Errors errors) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fel = getFieldErrorList(errors);

        for (FieldError fe : fel) {
            sb.setLength(0);
            sb.append("ValidationError");
            sb.append(" dataCount:[");
            if (dataValueObject != null) {
                sb.append(dataValueObject.getDataCount());
            }
            sb.append("]");
            sb.append(" code:[");
            sb.append(fe.getCode());
            sb.append("]");
            sb.append(" objectName:[");
            sb.append(fe.getObjectName());
            sb.append("]");
            sb.append(" field:[");
            sb.append(fe.getField());
            sb.append("]");
            sb.append(" rejectedValue:[");
            sb.append(fe.getRejectedValue());
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * ValidateStatus��Ԃ��B
     * @param dataValueObject DataValueObject
     * @param errors Errors
     * @return ValidateStatus
     */
    protected ValidateErrorStatus getValidateStatus(
            DataValueObject dataValueObject, Errors errors) {
        return validateStatus;
    }

    /**
     * Errors����FieldError�̃��X�g���擾����
     * @param errors Errors
     * @return List<FieldError>
     */
    public static List<FieldError> getFieldErrorList(Errors errors) {
        List<FieldError> resultList = new ArrayList<FieldError>();

        if (errors != null) {
            List<?> errs = errors.getAllErrors();
            for (Object errObj : errs) {
                if (errObj instanceof FieldError) {
                    FieldError fe = (FieldError) errObj;
                    resultList.add(fe);
                }
            }
        }

        return resultList;
    }

    /**
     * ���̓`�F�b�N�G���[�������擾����
     * @return int ���̓`�F�b�N�G���[����
     */
    public int getErrorFieldCount() {
        return errorFieldCount;
    }

    /**
     * ���̓`�F�b�N�G���[�̔z����擾����
     * @return Errors[] ���̓`�F�b�N�G���[�̔z��
     */
    public Errors[] getErrors() {
        return errorsQueue.toArray(new Errors[0]);
    }

    /**
     * ���O���x����ݒ肷��.<br>
     * <p>
     * <li>trace</li>
     * <li>debug</li>
     * <li>info</li>
     * <li>warn</li>
     * <li>error</li>
     * <li>fatal</li>
     * </p>
     * @param logLevel ���O���x��
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

}
