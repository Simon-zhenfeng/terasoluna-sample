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
package jp.terasoluna.batch.tutorial.sample005;

import jp.terasoluna.batch.tutorial.common.NyusyukkinData;
import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.db.DBCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.dao.QueryRowHandleDAO;
import jp.terasoluna.fw.file.dao.FileLineWriter;
import jp.terasoluna.fw.file.dao.FileUpdateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * �r�W�l�X���W�b�N�N���X�B(���o���e�[�u����csv�t�@�C���ɏo�͂���N���X)
 */
@Component
public class SMP005BLogic extends AbstractTransactionBLogic {

	private static final Log log = LogFactory.getLog(SMP005BLogic.class);

	@Autowired
	protected QueryRowHandleDAO queryRowHandleDAO;

	@Autowired
	@Qualifier(value = "csvFileUpdateDAO")
	protected FileUpdateDAO csvFileUpdateDAO;

	public int doMain(BLogicParam param) {

		// �W���u�I���R�[�h�i0:����I���A-1:�ُ�I���j
		int returnCode = 0;

		// �R���N�^
		Collector<NyusyukkinData> collector = new DBCollector<NyusyukkinData>(
				this.queryRowHandleDAO, "SMP005.selectNyusyukkin", null);

		// �t�@�C���o�͗p�s���C�^�̎擾(�����p)
		FileLineWriter<NyusyukkinData> fileLineWriterNyukin = csvFileUpdateDAO
				.execute("outputFile/SMP005_output_nyukin.csv",
						NyusyukkinData.class);

		// �t�@�C���o�͗p�s���C�^�̎擾(�o���p)
		FileLineWriter<NyusyukkinData> fileLineWriterSyukkin = csvFileUpdateDAO
				.execute("outputFile/SMP005_output_syukkin.csv",
						NyusyukkinData.class);

		try {
			// DB����擾�����f�[�^���i�[����I�u�W�F�N�g
			NyusyukkinData inputData = null;

			while (collector.hasNext()) {
				// DB����f�[�^���擾
				inputData = collector.next();

				// �t�@�C���փf�[�^���o�́i1�s�j
				// ���o���敪�ɂ��o�̓t�@�C����ύX
				if (inputData != null && inputData.getNyusyukkinKubun() == 0) {
					fileLineWriterNyukin.printDataLine(inputData);
				}
				if (inputData != null && inputData.getNyusyukkinKubun() == 1) {
					fileLineWriterSyukkin.printDataLine(inputData);
				}
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

			// �t�@�C���̃N���[�Y
			CollectorUtility.closeQuietly(fileLineWriterNyukin);
			CollectorUtility.closeQuietly(fileLineWriterSyukkin);

			// ����I�����̃��O
			if (returnCode == 0 && log.isInfoEnabled()) {
				log.info("�t�@�C���������݂�����ɏI�����܂����B");
			}
		}

		return returnCode;
	}
}
