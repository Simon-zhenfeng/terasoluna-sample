package jp.terasoluna.batch.functionsample.b002.b002001;

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
 * �񓯊��^�W���u�̃T���v��<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u���ɂP���ȏ�̃f�[�^�����݂��邱��<br>
 * EMPLOYEE2�e�[�u�������݂��Ă���f�[�^�����݂��Ȃ�����<br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���A<br>
 * EMPLOYEE�e�[�u���̂��ׂẴf�[�^���擾����B<br>
 * �����Ď擾�����f�[�^��EMPLOYEE2�e�[�u���ɑ}������T���v��<br>
 * </p>
 */
@Component
public class B002001BLogic extends AbstractTransactionBLogic {

    private static Logger logger = LoggerFactory.getLogger(B002001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    @Inject
    B002001BatchDao dao;

    @Override
    public int doMain(BLogicParam arg0) {
        if (logger.isInfoEnabled()) {
            logger.info("Start : (ThreadId:[{}],ThreadName:[{}])",
                    Thread.currentThread().getId(), Thread.currentThread().getName());
        }

        // �R���N�^����(���̓f�[�^�擾�@�\)
        Collector<B002001Param> collector = new DBCollector<B002001Param>(dao,
                "collectEmployee", null);

        try {
            while (collector.hasNext()) {
                B002001Param data = collector.next();
                logger.info("ID:" + data.getId() + " FIMILYNAME:"
                        + data.getFamilyName() + " FIRSTNAME:"
                        + data.getFirstName() + " AGE:" + data.getAge());

                dao.insertEmployee2(data);
            }

        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            // �t�@�C���̃N���[�Y
            CollectorUtility.closeQuietly(collector);
        }

        // ����I��
        return BATCH_NORMAL_END;
    }
}
