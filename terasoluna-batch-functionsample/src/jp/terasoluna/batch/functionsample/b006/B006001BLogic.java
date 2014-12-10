package jp.terasoluna.batch.functionsample.b006;

import jp.terasoluna.fw.batch.blogic.BLogic;
import jp.terasoluna.fw.batch.blogic.vo.BLogicParam;
import jp.terasoluna.fw.batch.dao.support.BatchUpdateSupport;
import jp.terasoluna.fw.batch.dao.support.BatchUpdateSupportImpl;
import jp.terasoluna.fw.batch.exception.BatchException;
import jp.terasoluna.fw.batch.util.BatchUtil;
import jp.terasoluna.fw.collector.Collector;
import jp.terasoluna.fw.collector.file.FileCollector;
import jp.terasoluna.fw.collector.util.CollectorUtility;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.file.dao.FileQueryDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * �o�b�`�X�V�œK���@�\�̃T���v��<br>
 * <p>
 * ���O�����FPERSONAL_INFORMATION�e�[�u�������݂��A101�`300�܂ł̃f�[�^�����݂��鎖<br>
 * </p>
 * <p>
 * �T���v�����e�Finsert,delete,update,insert...�̏��Ɋi�[����SQL��<br>
 * �o�b�`�X�V���s����insert100��,delete100��,update100���̏���<br>
 * �œK������Ă��邱�Ƃ��m�F����T���v��<br>
 * </p>
 */
@Component
public class B006001BLogic implements BLogic {

    private Log log = LogFactory.getLog(B006001BLogic.class);

    @Autowired
    private UpdateDAO updateDAO;

    @Autowired
    @Qualifier(value = "csvFileQueryDAO")
    private FileQueryDAO csvFileQueryDAO = null;

    @Autowired
    private PlatformTransactionManager transactionManager = null;

    public int execute(BLogicParam param) {

        TransactionStatus stat = null;

        // ��������
        int count = 0;

        // delete&update�pcode�̒l
        int i = 101;

        BatchUpdateSupport bus = new BatchUpdateSupportImpl(updateDAO);

        // �t�@�C�����͗p�R���N�^�̎擾
        Collector<SampleFileLineObject> collector = new FileCollector<SampleFileLineObject>(
                csvFileQueryDAO, "C:\\tmp\\inputB006.csv",
                SampleFileLineObject.class);

        try {
            // �g�����U�N�V�����J�n
            stat = BatchUtil.startTransaction(transactionManager);
            while (collector.hasNext()) {
                // �f�[�^���̓ǂݍ���
                SampleFileLineObject insertData = collector.next();

                // insert(00001�`00100)
                bus.addBatch("B006001.insertData01", insertData);

                // delete(00101�`00200)
                SampleFileLineObject deleteData = new SampleFileLineObject();
                deleteData.setCode(String.valueOf(i));
                bus.addBatch("B006001.deleteData01", deleteData);

                // update(00301�`00300)
                SampleFileLineObject updateData = new SampleFileLineObject(
                        String.valueOf((i + 100)), "hoge", "hoge", "hoge",
                        "hoge", "hoge", "hoge", "hoge", "hoge", "hoge");
                bus.addBatch("B006001.updateData01", updateData);

                i++;

            }

            // �o�b�`�X�V�̎��s
            count = bus.executeBatch();
            if (log.isInfoEnabled()) {
                log.info("���������F" + count + "��");
                log.info("�o�b�`�X�V����");
            }
            // �g�����U�N�V�����R�~�b�g
            BatchUtil.commitTransaction(transactionManager, stat);
        } catch (Exception e) {
            throw new BatchException(e);
        } finally {
            // �R���N�^�̃N���[�Y
            CollectorUtility.closeQuietly(collector);
            // �g�����U�N�V�����I��
            BatchUtil.endTransaction(transactionManager, stat);
        }

        return 0;
    }
}
