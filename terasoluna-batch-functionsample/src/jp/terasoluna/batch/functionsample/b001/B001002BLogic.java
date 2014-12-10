package jp.terasoluna.batch.functionsample.b001;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.dao.support.BatchUpdateSupport;
import jp.terasoluna.fw.batch.dao.support.BatchUpdateSupportImpl;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.batch.util.BatchUtil;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * �����^�W���u�E�g�����U�N�V�����Ǘ��@�\�̃T���v���Q<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u�����쐬���Ă���A<br>
 *�P���ȏ�̃f�[�^�����݂��邱��<br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���ADB���Q�Ƃ��ADB���X�V����T���v��<br>
 * BLogic���p�����A�r�W�l�X���W�b�N���Ńg�����U�N�V�����̊Ǘ����s��<br>
 * (�f�[�^��10�����ɍX�V����)<br>
 * �����I����́A���ׂẴf�[�^����ؑ��Y�ɏ�����������<br>
 * </p>
 */
@Component
public class B001002BLogic implements BLogic {

    private Log log = LogFactory.getLog(B001002BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    @Autowired
    @Qualifier("queryRowHandleDAO")
    private QueryRowHandleDAO queryRowHandleDAO = null;

    @Autowired
    @Qualifier("updateDAO")
    private UpdateDAO updateDAO = null;

    @Autowired
    @Qualifier("transactionManager")
    private PlatformTransactionManager transactionManager = null;

    public int execute(BLogicParam arg0) {

        TransactionStatus outerStat = null;
        Collector<B001002Param> collector = null;
        BatchUpdateSupport bus = new BatchUpdateSupportImpl();

        outerStat = BatchUtil.startTransaction(transactionManager);

        try {
            B001002Param param = new B001002Param();

            collector = new DBCollector<B001002Param>(queryRowHandleDAO,
                    "B001002.selectEmployee", param);

            // REQUIRES_NEW�̃g�����U�N�V������`�𐶐�
            TransactionDefinition def = BatchUtil.getTransactionDefinition(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW,
                    TransactionDefinition.ISOLATION_DEFAULT,
                    TransactionDefinition.TIMEOUT_DEFAULT, false);

            TransactionStatus innerStat = null;

            try {
                // �g�����U�N�V�����J�n
                innerStat = BatchUtil.startTransaction(transactionManager, def);

                int commitCount = 0;

                while (collector.hasNext()) {
                    B001002Param data = collector.next();

                    if (log.isInfoEnabled()) {
                        log
                                .info("ID:" + data.getId() + " FIMILYNAME:"
                                        + data.getFamilyName() + " FIRSTNAME:"
                                        + data.getFirstName() + " AGE:"
                                        + data.getAge());
                    }

                    data.setFamilyName("���");
                    data.setFirstName("���Y");

                    bus.addBatch("B001002.updateEmployee", data);
                    // 10�����ƂɃo�b�`�X�V���s�ƃR�~�b�g
                    if (bus.size() >= 10) {
                        if (log.isInfoEnabled()) {
                            log.info("�o�b�`�X�V���s�ƃR�~�b�g");
                        }
                        bus.executeBatch(updateDAO);

                        // �g�����U�N�V�����R�~�b�g���ĊJ�n����
                        innerStat = BatchUtil.commitRestartTransaction(
                                transactionManager, innerStat, def);
                        commitCount++;
                    }
                }
                bus.executeBatch(updateDAO);

                BatchUtil.commitTransaction(transactionManager, innerStat);
                BatchUtil.commitTransaction(transactionManager, outerStat);
            } catch (BatchException e) {
                throw e;
            } catch (Exception e) {
                throw new BatchException(e);
            } finally {
                BatchUtil.endTransaction(transactionManager, innerStat);
            }
        } catch (BatchException e) {
            throw e;
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            CollectorUtility.closeQuietly(collector);
            BatchUtil.endTransaction(transactionManager, outerStat);
        }

        return BATCH_NORMAL_END;

    }
}
