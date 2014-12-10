package jp.terasoluna.batch.functionsample.b002;

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
 * �񓯊��^�W���u�̃T���v��<br>
 * <p>
 * ���O�����FEMPLOYEE�e�[�u���ɂP���ȏ�̃f�[�^�����݂��邱��<br>
 * EMPLOYEE2�e�[�u�������݂��Ă���f�[�^�����݂��Ȃ�����<br>
 * </p>
 * <p>
 * �T���v�����e�F���̓f�[�^�擾�@�\���g�p���A<br>
 * EMPLOYEE�e�[�u���̂��ׂẴf�[�^���擾����B<br>
 * �����Ď擾�����f�[�^���o�b�`�X�V�œK���@�\���g�p���A<br>
 * EMPLOYEE2�e�[�u���ɑ}������T���v��<br>
 * </p>
 */
@Component
public class B002001BLogic extends AbstractTransactionBLogic {

    private static Log logger = LogFactory.getLog(B002001BLogic.class);

    private static final int BATCH_NORMAL_END = 0;

    @Autowired
    @Qualifier("queryRowHandleDAO")
    private QueryRowHandleDAO queryRowHandleDAO = null;

    @Autowired
    @Qualifier("updateDAO")
    private UpdateDAO updateDAO = null;

    @Override
    public int doMain(BLogicParam arg0) {
        if (logger.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Start : (");
            sb.append("ThreadId:[");
            sb.append(Thread.currentThread().getId());
            sb.append("],");
            sb.append("ThreadName:[");
            sb.append(Thread.currentThread().getName());
            sb.append("])");
            logger.info(sb.toString());
        }

        // �R���N�^����(���̓f�[�^�擾�@�\)
        Collector<B002001Param> collector = new DBCollector<B002001Param>(
                queryRowHandleDAO, "B002001.selectEmployeeList", null);

        // �o�b�`�X�V�T�|�[�g����(�o�b�`�X�V�œK���@�\)
        BatchUpdateSupport bus = new BatchUpdateSupportImpl();

        try {
            while (collector.hasNext()) {
                B002001Param data = collector.next();
                logger.info("ID:" + data.getId() + " FIMILYNAME:"
                        + data.getFamilyName() + " FIRSTNAME:"
                        + data.getFirstName() + " AGE:" + data.getAge());

                bus.addBatch("B002001.insertEmployee", data);
            }

            // �o�b�`�X�V���s
            bus.executeBatch(updateDAO);

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
