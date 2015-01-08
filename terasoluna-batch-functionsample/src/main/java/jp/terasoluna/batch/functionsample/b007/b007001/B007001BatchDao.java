package jp.terasoluna.batch.functionsample.b007.b007001;

import jp.terasoluna.batch.functionsample.b007.CsvRecord;

public interface B007001BatchDao {

    /**
     * EMPLOY�e�[�u���̃f�[�^���폜����B
     * @param object SQL�p�����[�^�����I�u�W�F�N�g
     * @param handler ResultHandler
     */
    public void deleteEmployees();

    /**
     * EMPLOYEE�e�[�u���Ƀf�[�^��}������B
     * @param param �}���ΏۃI�u�W�F�N�g
     * @return �}������
     */
    public int insertEmployee(CsvRecord param);

}
