/*
 * Copyright (c) 2015 NTT DATA Corporation
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
package jp.terasoluna.batch.tutorial.sample003;

import javax.inject.Inject;
import javax.inject.Named;

import jp.terasoluna.batch.tutorial.common.NyusyukkinData;
import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileValidateCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.file.dao.FileQueryDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

/**
 * �r�W�l�X���W�b�N�N���X�B(CSV�t�@�C����ǂݍ��݁ADB�Ƀf�[�^��}������j
 */
@Component
public class SMP003BLogic extends AbstractTransactionBLogic {

    private static final Logger log = LoggerFactory
            .getLogger(SMP003BLogic.class);

    @Inject
    protected SMP003Dao dao;

    @Inject
    @Named("csvFileQueryDAO")
    protected FileQueryDAO csvFileQueryDAO;

    @Inject
    @Named("jsr303BeanValidator")
    protected Validator validator;

    public int doMain(BLogicParam param) {

        // �W���u�I���R�[�h(0:����I���A-1:�ُ�I���j
        int returnCode = 0;

        CustomValidationErrorHandler customValidationErrorHandler = new CustomValidationErrorHandler();

        // �R���N�^
        Collector<NyusyukkinData> collector = new FileValidateCollector<NyusyukkinData>(
                this.csvFileQueryDAO, "inputFile/SMP003_input.csv",
                NyusyukkinData.class, validator, customValidationErrorHandler);

        try {
            // �t�@�C������擾�����f�[�^���i�[����I�u�W�F�N�g
            NyusyukkinData inputData = null;
            while (collector.hasNext()) {

                // �t�@�C������f�[�^���擾
                inputData = collector.next();

                // DB�X�V����
                dao.insertNyusyukkinData(inputData);
            }
        } catch (DataAccessException e) {
            if (log.isErrorEnabled()) {
                log.error("�f�[�^�A�N�Z�X�G���[���������܂���", e);
            }

            returnCode = -1;
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("�G���[���������܂���", e);
            }

            returnCode = -1;
        } finally {
            // �R���N�^�̃N���[�Y
            CollectorUtility.closeQuietly(collector);

            // ����I�����Ƀ��O�c��
            if (returnCode == 0 && log.isInfoEnabled()) {
                log.info("DB�̍X�V������ɏI�����܂����B");
            }
        }

        return returnCode;
    }
}
