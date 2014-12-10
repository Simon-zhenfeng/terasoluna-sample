package jp.terasoluna.batch.functionsample.b001;

import jp.terasoluna.fw.batch.blogic.AbstractTransactionBLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.dao.support.BatchUpdateSupport;
import jp.terasoluna.fw.batch.dao.support.BatchUpdateSupportImpl;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.db.DBCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.dao.QueryRowHandleDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * �����^�W���u�E�g�����U�N�V�����Ǘ��@�\�̃T���v���P<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u�����쐬���Ă���A<br>
 *�P���ȏ�̃f�[�^�����݂��邱��<br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���ADB���Q�Ƃ��ADB���X�V����T���v��<br>
 * AbstractTransactionBLogic���p�����t���[�����[�N���Ƀg�����U�N�V�����Ǘ���C����<br>
 * (�f�[�^�͑S���ꊇ�ɍX�V����)<br>
 * �����I����́A���ׂẴf�[�^����ؑ��Y�ɏ�����������<br>
 * </p>
 */
@Component
public class B001001BLogic extends AbstractTransactionBLogic {

    private Log log = LogFactory.getLog(B001001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    @Autowired
    @Qualifier("queryRowHandleDAO")
    private QueryRowHandleDAO queryRowHandleDAO = null;

    @Autowired
    @Qualifier("updateDAO")
    private UpdateDAO updateDAO = null;

    public int doMain(BLogicParam arg0) {

        Collector<B001001Param> collector = null;
        BatchUpdateSupport bus = new BatchUpdateSupportImpl();

        try {
            B001001Param param = new B001001Param();
            collector = new DBCollector<B001001Param>(queryRowHandleDAO,
                    "B001001.selectEmployee", param);

            while (collector.hasNext()) {
                B001001Param data = collector.next();

                if (log.isInfoEnabled()) {
                    log.info("ID:" + data.getId() + " FIMILYNAME:"
                            + data.getFamilyName() + " FIRSTNAME:"
                            + data.getFirstName() + " AGE:" + data.getAge());
                }

                data.setFamilyName("���");
                data.setFirstName("���Y");

                bus.addBatch("B001001.updateEmployee", data);

            }
            bus.executeBatch(updateDAO);

        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            CollectorUtility.closeQuietly(collector);
        }
        return BATCH_NORMAL_END;

    }

}
