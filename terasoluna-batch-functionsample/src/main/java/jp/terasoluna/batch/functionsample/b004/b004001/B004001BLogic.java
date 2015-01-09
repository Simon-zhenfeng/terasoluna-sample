package jp.terasoluna.batch.functionsample.b004.b004001;

import javax.inject.Inject;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.file.util.FileControl;

import org.springframework.stereotype.Component;

/**
 * �t�@�C������@�\�̃T���v��<br>
 * <p>
 * ���O�����F�Ȃ�<br>
 * </p>
 * <p>
 * �T���v�����e�F�t�@�C������@�\�̃T���v��<br>
 * �W���u�����s�����ۂɁuC:\\tmp\\input.csv�v���uC:\\tmp\\outputB004001.csv�v��<br>
 * �R�s�[����Ă��邱�Ƃ��m�F����<br>
 * </p>
 */
@Component
public class B004001BLogic implements BLogic {

    @Inject
    FileControl fileControl = null;

    public int execute(BLogicParam arg0) {

        fileControl
                .copyFile("C:\\tmp\\input.csv", "C:\\tmp\\outputB004001.csv");

        return 0;
    }

}
