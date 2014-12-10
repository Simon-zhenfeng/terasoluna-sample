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
package jp.terasoluna.batch.tutorial.sample004;

import java.util.Date;
import java.util.Map;

import jp.terasoluna.batch.tutorial.common.NyusyukkinData;
import jp.terasoluna.batch.tutorial.common.NyusyukkinFileOutput;
import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.collector.util.ControlBreakChecker;
import jp.terasoluna.fw.file.dao.FileLineWriter;
import jp.terasoluna.fw.file.dao.FileQueryDAO;
import jp.terasoluna.fw.file.dao.FileUpdateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * �r�W�l�X���W�b�N�N���X�B�iCSV�t�@�C����ǂݍ��݁Acsv�t�@�C���ɏo�͂���N���X�j
 */
@Component
public class SMP004BLogic extends AbstractTransactionBLogic {

	private static final Log log = LogFactory.getLog(SMP004BLogic.class);

	@Autowired
	@Qualifier(value = "csvFileQueryDAO")
	protected FileQueryDAO csvFileQueryDAO;

	@Autowired
	@Qualifier(value = "csvFileUpdateDAO")
	protected FileUpdateDAO csvFileUpdateDAO;

	public int doMain(BLogicParam param) {

		// �W���u�I���R�[�h�i0:����I���A-1:�ُ�I���j
		int returnCode = 0;

		// �R���N�^
		Collector<NyusyukkinData> collector = new FileCollector<NyusyukkinData>(
				this.csvFileQueryDAO, "inputFile/SMP004_input.csv",
				NyusyukkinData.class);

		// �t�@�C���o�͗p�s���C�^�̎擾
		FileLineWriter<NyusyukkinFileOutput> fileLineWriter = csvFileUpdateDAO
				.execute("outputFile/SMP004_output.csv",
						NyusyukkinFileOutput.class);

		try {
			// �t�@�C������擾�����f�[�^���i�[����I�u�W�F�N�g
			NyusyukkinData inputData = null;

			// �����̃J�E���g�p
			int nyukinNum = 0;
			// �o���̃J�E���g�p
			int syukkinNum = 0;
			// �������v�p
			int nyukinSum = 0;
			// �o�����v�p
			int syukkinSum = 0;

			while (collector.hasNext()) {
				// �t�@�C������f�[�^���擾
				inputData = collector.next();

				// �R���g���[���u���C�N����
				// �x�X���A������ɕύX������ꍇ
				boolean ctrlBreak = ControlBreakChecker.isBreak(collector,
						"torihikibi", "shitenName");

				// ���o���敪�̃J�E���g�A���v�v�Z
				if (inputData != null && inputData.getNyusyukkinKubun() == 0) {
					syukkinNum++;
					syukkinSum += inputData.getKingaku();
				} else if (inputData != null
						&& inputData.getNyusyukkinKubun() == 1) {
					nyukinNum++;
					nyukinSum += inputData.getKingaku();
				}

				// �R���g���[���u���C�N����
				if (ctrlBreak) {
					// �L�[�f�[�^���}�b�v�Ŏ擾
					Map<String, Object> brkMap = ControlBreakChecker
							.getBreakKey(collector, "torihikibi", "shitenName");
					Date torihikibi = null;
					String shitenName = null;
					if (brkMap.containsKey("torihikibi")) {
						torihikibi = (Date) brkMap.get("torihikibi");
					} else {
						torihikibi = inputData.getTorihikibi();
					}
					if (brkMap.containsKey("shitenName")) {
						shitenName = (String) brkMap.get("shitenName");
					} else {
						shitenName = inputData.getShitenName();
					}

					// �R���g���[���u���C�N���̃f�[�^�̍쐬
					NyusyukkinFileOutput outputData = new NyusyukkinFileOutput();
					outputData.setTorihikibi(torihikibi);
					outputData.setShitenName(shitenName);
					outputData.setNyukinNum(nyukinNum);
					outputData.setNyukinSum(nyukinSum);
					outputData.setSyukkinNum(syukkinNum);
					outputData.setSyukkinSum(syukkinSum);

					// �f�[�^���t�@�C���֏o�́i1�s�j
					fileLineWriter.printDataLine(outputData);

					// ���o���敪�J�E���g�̏�����
					nyukinNum = 0;
					syukkinNum = 0;
					nyukinSum = 0;
					syukkinSum = 0;
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
			CollectorUtility.closeQuietly(fileLineWriter);

			// ����I�����̃��O
			if (returnCode == 0 && log.isInfoEnabled()) {
				log.info("�t�@�C���������݂�����ɏI�����܂����B");
			}
		}

		return returnCode;
	}
}
