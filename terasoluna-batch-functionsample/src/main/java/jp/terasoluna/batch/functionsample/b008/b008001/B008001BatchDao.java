package jp.terasoluna.batch.functionsample.b008.b008001;

import jp.terasoluna.batch.functionsample.b008.ZipCode;

public interface B008001BatchDao {

    /**
     * ZIPCODE�e�[�u���̍폜
     * @return �폜����
     */
    int deleteZipCode();

    /**
     * ZIPCODE�e�[�u���ւ̃f�[�^�}��
     * @param zipCode �}���ΏۃI�u�W�F�N�g
     * @return �}������
     */
    int insertZipCode(ZipCode zipCode);

}
