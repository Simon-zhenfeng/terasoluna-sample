package jp.terasoluna.batch.functionsample.b003.b003001;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;

import org.springframework.stereotype.Component;

/**
 * ��O�n���h�����O�@�\�̃T���v��<br>
 * <p>
 * ���O�����F�Ȃ�<br>
 * </p>
 * <p>
 * �T���v�����e�F��O�n���h�����O�@�\�̃T���v��<br>
 * �W���u�����s�����ۂ�B003001ExceptionHandler�N���X���g�p����<br>
 * ���O��"RuntimeException is Thrown..."���\������邱�Ƃ��m�F����B<br>
 * </p>
 */
@Component
public class B003001BLogic implements BLogic {

    public int execute(BLogicParam arg0) {

        throw new RuntimeException();

    }

}
