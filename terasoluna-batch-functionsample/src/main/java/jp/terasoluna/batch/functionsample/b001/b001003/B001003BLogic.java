package jp.terasoluna.batch.functionsample.b001.b001003;

import javax.inject.Inject;

import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.db.DBCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * �����^�W���u�E�g�����U�N�V�����Ǘ��@�\�̃T���v���R<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u�����쐬���Ă���A<br>
 * �P���ȏ�̃f�[�^�����݂��邱��<br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���ADB���Q�Ƃ��ADB���o�b�`�X�V����T���v��<br>
 * AbstractTransactionBLogic���p�����t���[�����[�N���Ƀg�����U�N�V�����Ǘ���C����<br>
 * (�f�[�^�͑S���ꊇ�ɍX�V����)<br>
 * �����I����́A���ׂẴf�[�^����ؑ��Y�ɏ�����������<br>
 * </p>
 */
@Component
public class B001003BLogic extends AbstractTransactionBLogic {

    private Logger log = LoggerFactory.getLogger(B001003BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    @Inject
    private B001003BatchDao dao;

    public int doMain(BLogicParam arg0) {

        Collector<B001003Param> collector = null;

        try {
            B001003Param param = new B001003Param();
            collector = new DBCollector<B001003Param>(dao, "collectEmployee",
                    param);

            while (collector.hasNext()) {
                B001003Param data = collector.next();

                if (log.isInfoEnabled()) {
                    log.info("ID:" + data.getId() + " FIMILYNAME:"
                            + data.getFamilyName() + " FIRSTNAME:"
                            + data.getFirstName() + " AGE:" + data.getAge());
                }

                data.setFamilyName("���");
                data.setFirstName("���Y");

                dao.updateEmployee(data);
            }
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            CollectorUtility.closeQuietly(collector);
        }
        return BATCH_NORMAL_END;

    }

}
