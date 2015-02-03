package jp.terasoluna.batch.functionsample.b001.b001002;

import javax.inject.Inject;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.batch.util.BatchUtil;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.db.DBCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * �����^�W���u�E�g�����U�N�V�����Ǘ��@�\�̃T���v���Q<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u�����쐬���Ă���A<br>
 * �P���ȏ�̃f�[�^�����݂��邱��<br>
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

    private Logger log = LoggerFactory.getLogger(B001002BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    @Inject
    B001002Dao dao;

    @Inject
    PlatformTransactionManager transactionManager;

    public int execute(BLogicParam arg0) {

        TransactionStatus outerStat = null;
        Collector<B001002Param> collector = null;

        outerStat = BatchUtil.startTransaction(transactionManager);

        try {
            B001002Param param = new B001002Param();

            collector = new DBCollector<B001002Param>(dao, "collectEmployee",
                    param);

            // REQUIRES_NEW�̃g�����U�N�V������`�𐶐�
            TransactionDefinition def = BatchUtil.getTransactionDefinition(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW,
                    TransactionDefinition.ISOLATION_DEFAULT,
                    TransactionDefinition.TIMEOUT_DEFAULT, false);

            TransactionStatus innerStat = null;

            try {
                // �g�����U�N�V�����J�n
                innerStat = BatchUtil.startTransaction(transactionManager, def);

                int updateCount = 0;
                int commitCount = 0;

                while (collector.hasNext()) {
                    B001002Param data = collector.next();

                    if (log.isInfoEnabled()) {
                        log.info("ID:" + data.getId() + " FIMILYNAME:"
                                + data.getFamilyName() + " FIRSTNAME:"
                                + data.getFirstName() + " AGE:" + data.getAge());
                    }

                    data.setFamilyName("���");
                    data.setFirstName("���Y");

                    dao.updateEmployee(data);
                    updateCount++;

                    // 10�����ƂɃR�~�b�g
                    if (updateCount % 10 == 0) {
                        if (log.isInfoEnabled()) {
                            log.info("�R�~�b�g");
                        }
                        // �g�����U�N�V�����R�~�b�g���ĊJ�n����
                        innerStat = BatchUtil.commitRestartTransaction(
                                transactionManager, innerStat, def);
                        commitCount++;
                    }
                }

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
