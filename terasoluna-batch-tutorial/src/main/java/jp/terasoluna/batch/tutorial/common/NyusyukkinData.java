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
package jp.terasoluna.batch.tutorial.common;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import jp.terasoluna.fw.file.annotation.FileFormat;
import jp.terasoluna.fw.file.annotation.InputFileColumn;
import jp.terasoluna.fw.file.annotation.OutputFileColumn;

/**
 * ���o�����̃p�����[�^�N���X�B
 */
@FileFormat(overWriteFlg = true, fileEncoding = "MS932")
public class NyusyukkinData {

    /**
     * �x�X��
     */
    @InputFileColumn(columnIndex = 0)
    @OutputFileColumn(columnIndex = 0)
    private String shitenName;

    /**
     * �ڋqID
     */
    @InputFileColumn(columnIndex = 1)
    @OutputFileColumn(columnIndex = 1)
    @NotEmpty
    private String kokyakuId;

    /**
     * ���o���敪 0:�o�� 1:����
     */
    @InputFileColumn(columnIndex = 2)
    @OutputFileColumn(columnIndex = 2)
    private int nyusyukkinKubun;

    /**
     * ������z
     */
    @InputFileColumn(columnIndex = 3)
    @OutputFileColumn(columnIndex = 3)
    private int kingaku;

    /**
     * �����
     */
    @InputFileColumn(columnIndex = 4, columnFormat = "yyyyMMdd")
    @OutputFileColumn(columnIndex = 4, columnFormat = "yyyyMMdd")
    private Date torihikibi;

    /**
     * �x�X�����擾����B
     * @return shitenName
     */
    public String getShitenName() {
        return shitenName;
    }

    /**
     * �x�X����ݒ肷��B
     * @param shitenName
     */
    public void setShitenName(String shitenName) {
        this.shitenName = shitenName;
    }

    /**
     * �ڋqID���擾����B
     * @return kokyakuId
     */
    public String getKokyakuId() {
        return kokyakuId;
    }

    /**
     * �ڋqID��ݒ肷��B
     * @param kokyakuId
     */
    public void setKokyakuId(String kokyakuId) {
        this.kokyakuId = kokyakuId;
    }

    /**
     * ���o���敪���擾����B
     * @return nyusyukkinKubun
     */
    public int getNyusyukkinKubun() {
        return nyusyukkinKubun;
    }

    /**
     * ���o���敪��ݒ肷��B
     * @param nyusyukkinKubun
     */
    public void setNyusyukkinKubun(int nyusyukkinKubun) {
        this.nyusyukkinKubun = nyusyukkinKubun;
    }

    /**
     * ������z���擾����B
     * @return kingaku
     */
    public int getKingaku() {
        return kingaku;
    }

    /**
     * ������z��ݒ肷��B
     * @param kingaku
     */
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }

    /**
     * ��������擾����B
     * @return torihikibi
     */
    public Date getTorihikibi() {
        return torihikibi;
    }

    /**
     * �������ݒ肷��B
     * @param torihikibi
     */
    public void setTorihikibi(Date torihikibi) {
        this.torihikibi = torihikibi;
    }
}
