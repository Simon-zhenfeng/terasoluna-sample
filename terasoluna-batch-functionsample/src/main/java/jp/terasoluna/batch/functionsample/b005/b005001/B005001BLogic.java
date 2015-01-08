package jp.terasoluna.batch.functionsample.b005.b005001;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.util.MessageUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * ���b�Z�[�W�Ǘ��@�\�̃T���v��<br>
 * <p>
 * ���O�����F�Ȃ�<br>
 * </p>
 * <p>
 * �T���v�����e�F���b�Z�[�W�Ǘ��@�\�̃T���v��<br>
 * �W���u�����s�����ۂɁumessages.properties�v�ɒ�`�������b�Z�[�W��<br>
 * �Ăяo���Ă��邱�Ƃ��m�F����<br>
 * </p>
 */
@Component
public class B005001BLogic implements BLogic {

    private static Log log = LogFactory.getLog(B005001BLogic.class);

    public int execute(BLogicParam arg0) {

        log.info(MessageUtil.getMessage("message.test1"));

        String[] arg1 = { "[test10]" };
        log.info(MessageUtil.getMessage("message.test2", arg1));

        String[] arg2 = { "[test20]", "[test21]" };
        log.info(MessageUtil.getMessage("message.test3", arg2));

        String[] arg3 = { "[test30]", "[test31]", "[test32]" };
        log.info(MessageUtil.getMessage("message.test4", arg3));

        return 0;
    }

}
