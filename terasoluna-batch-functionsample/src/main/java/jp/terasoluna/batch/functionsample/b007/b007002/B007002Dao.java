package jp.terasoluna.batch.functionsample.b007.b007002;

import org.apache.ibatis.session.ResultHandler;

public interface B007002Dao {
    /**
     * EMPLOYEE�e�[�u���̃f�[�^���擾����B
     * @param object SQL�p�����[�^�����I�u�W�F�N�g
     * @param handler ResultHandler
     */
    void collectEmployee(Object object, ResultHandler handler);
}
