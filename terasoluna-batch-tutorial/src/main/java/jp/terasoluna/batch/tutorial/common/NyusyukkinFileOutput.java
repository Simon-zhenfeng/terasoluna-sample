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

import jp.terasoluna.fw.file.annotation.FileFormat;
import jp.terasoluna.fw.file.annotation.OutputFileColumn;

/**
 * ���o�����̃t�@�C���o�̓p�����[�^�N���X�B
 */
@FileFormat(overWriteFlg = true, fileEncoding = "MS932")
public class NyusyukkinFileOutput {

    /**
     * �����
     */
    @OutputFileColumn(columnIndex = 0, columnFormat = "yyyyMMdd")
    private Date torihikibi;

    /**
     * �x�X��
     */
    @OutputFileColumn(columnIndex = 1)
    private String shitenName;

    /**
     * ������
     */
    @OutputFileColumn(columnIndex = 2)
    private int nyukinNum;

    /**
     * �o����
     */
    @OutputFileColumn(columnIndex = 3)
    private int syukkinNum;

    /**
     * �������v
     */
    @OutputFileColumn(columnIndex = 4)
    private int nyukinSum;

    /**
     * �o�����v
     */
    @OutputFileColumn(columnIndex = 5)
    private int syukkinSum;

    /**
     * ��������擾����B
     * 
     * @return torihikibi
     */
    public Date getTorihikibi() {
        return torihikibi;
    }

    /**
     * �������ݒ肷��B
     * 
     * @param torihikibi
     */
    public void setTorihikibi(Date torihikibi) {
        this.torihikibi = torihikibi;
    }

    /**
     * �x�X�����擾����B
     * 
     * @return shitenName
     */
    public String getShitenName() {
        return shitenName;
    }

    /**
     * �x�X����ݒ肷��B
     * 
     * @param shitenName
     */
    public void setShitenName(String shitenName) {
        this.shitenName = shitenName;
    }

    /**
     * �����񐔂��擾����B
     * 
     * @return nyukinNum
     */
    public int getNyukinNum() {
        return nyukinNum;
    }

    /**
     * �����񐔂�ݒ肷��B
     * 
     * @param nyukinNum
     */
    public void setNyukinNum(int nyukinNum) {
        this.nyukinNum = nyukinNum;
    }

    /**
     * �o���񐔂��擾����B
     * 
     * @return syukkinNum
     */
    public int getSyukkinNum() {
        return syukkinNum;
    }

    /**
     * �o���񐔂�ݒ肷��B
     * 
     * @param syukkinNum
     */
    public void setSyukkinNum(int syukkinNum) {
        this.syukkinNum = syukkinNum;
    }

    /**
     * �������v���擾����B
     * 
     * @return nyukinSum
     */
    public int getNyukinSum() {
        return nyukinSum;
    }

    /**
     * �������v��ݒ肷��B
     * 
     * @param nyukinSum
     */
    public void setNyukinSum(int nyukinSum) {
        this.nyukinSum = nyukinSum;
    }

    /**
     * �o�����v���擾����B
     * 
     * @return syukkinSum
     */
    public int getSyukkinSum() {
        return syukkinSum;
    }

    /**
     * �o�����v��ݒ肷��B
     * 
     * @param syukkinSum
     */
    public void setSyukkinSum(int syukkinSum) {
        this.syukkinSum = syukkinSum;
    }

}
