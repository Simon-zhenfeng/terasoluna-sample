package jp.terasoluna.batch.functionsample.b009.b009002;

import org.apache.ibatis.session.ResultHandler;

public interface B009002BatchDao {

    /**
     * EMPLOYEE2�e�[�u���̃f�[�^���폜����B
     * @return �폜����
     */
    int deleteEmployee2();

    /**
     * EMPLOYEE3�e�[�u���̃f�[�^���擾����B
     * @param object SQL�p�����[�^�����I�u�W�F�N�g
     * @param handler ResultHandler
     */
    void collectEmployee(Object object, ResultHandler handler);

    /**
     * EMPLOYEE2�e�[�u���Ƀf�[�^��}������B
     * @param record �}���ΏۃI�u�W�F�N�g
     * @return �}������
     */
    int insertEmployee2(CsvRecord record);

}
