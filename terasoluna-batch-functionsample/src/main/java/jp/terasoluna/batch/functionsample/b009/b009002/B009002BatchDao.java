package jp.terasoluna.batch.functionsample.b009.b009002;

import jp.terasoluna.batch.functionsample.b009.CsvRecord;

import org.apache.ibatis.session.ResultHandler;

public interface B009002BatchDao {

    /**
     * EMPLOYEE3�e�[�u���̃f�[�^���擾����B
     * @param object SQL�p�����[�^�����I�u�W�F�N�g
     * @param handler ResultHandler
     */
    public void collectEmployee(Object object, ResultHandler handler);

    /**
     * EMPLOYEE2�e�[�u���Ƀf�[�^��}������B
     * @param record �}���ΏۃI�u�W�F�N�g
     * @return �}������
     */
    public int insertEmployee2(CsvRecord record);

}
