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

package jp.terasoluna.batch.tutorial.sample000;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import jp.terasoluna.batch.tutorial.common.NyusyukkinData;
import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * �r�W�l�X���W�b�N�N���X�B(���o���e�[�u���Ƀ����_���f�[�^���o�͂���N���X)
 */
@Component
public class SMP000BLogic extends AbstractTransactionBLogic {

	/**
	 * ���O�N���X�B
	 */
	private static final Log log = LogFactory.getLog(SMP000BLogic.class);

	@Autowired
	protected UpdateDAO updateDAO;

	@Override
	public int doMain(BLogicParam param) {

		int resultNum = 0;

		// DB�̍쐬�s���B�f�t�H���g�Ƃ���100��ݒ�
		int maxNumber = 100;

		// ���������݂����ꍇ�͓����e�[�u���̍쐬�s����ύX
		if (null != param.getJobArgNm1()) {
			maxNumber = Integer.parseInt(param.getJobArgNm1());
		}

		// �f�[�^�����p�����_���֐�
		Random random = new Random();

		NyusyukkinData nyusyukkin = new NyusyukkinData();

		try {
			// ���o���e�[�u���̃f�[�^���N���A
			updateDAO.execute("SMP000.deleteNyusyukkin", null);

			for (int count = 1; count <= maxNumber; count++) {

				// �����_���Ɏx�X��������
				String shitenName = "";
				int shitenNum = random.nextInt(3) + 1;
				if (shitenNum == 1) {
					shitenName = "����";
				} else if (shitenNum == 2) {
					shitenName = "���";
				} else if (shitenNum == 3) {
					shitenName = "��t";
				}
				String kokyakuId = String.valueOf(random.nextInt(1000) + 1);

				// �p�f�B���O(0����)
				while (kokyakuId.length() < 4) {
					kokyakuId = "0" + kokyakuId;
				}
				int nyusyukkinKubun = random.nextInt(2);
				int kingaku = random.nextInt(1000000) + 1;

				// ���t��(2010/01/01�`2011/12/31�̊Ԃ�)�����_���ō쐬�B
				StringBuilder hiduke = new StringBuilder();
				hiduke.append(String.valueOf(2010 + random.nextInt(2)) + "/");
				int month = random.nextInt(12) + 1;
				hiduke.append(String.valueOf(month) + "/");
				if (month == 4 || month == 6 || month == 9 || month == 11) {
					hiduke.append(String.valueOf(random.nextInt(30) + 1));
				} else if (month == 2) {
					hiduke.append(String.valueOf(random.nextInt(28) + 1));
				} else {
					hiduke.append(String.valueOf(random.nextInt(31) + 1));
				}
				Date date = DateFormat.getDateInstance().parse(
						hiduke.toString());

				// �P�s���̃I�u�W�F�N�g�쐬
				nyusyukkin.setShitenName(shitenName);
				nyusyukkin.setKokyakuId(kokyakuId);
				nyusyukkin.setNyusyukkinKubun(nyusyukkinKubun);
				nyusyukkin.setKingaku(kingaku);
				nyusyukkin.setTorihikibi(date);

				// DB�ւ̃f�[�^�o�^
				updateDAO.execute("SMP000.insertNyusyukkin", nyusyukkin);
			}

			if (log.isDebugEnabled()) {
				log.debug("end:NyusyukkinReset");
			}

		} catch (Exception e) {
			// ��O����(�G���[�R�[�h�̐ݒ�)
			resultNum = -1;
		}
		return resultNum;
	}
}
