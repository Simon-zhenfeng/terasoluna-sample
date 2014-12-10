*******************************************************************************
      TERASOLUNA Batch Framework for Java 3.x
      �@�\�T���v�� �����菇�ɂ���

      Copyright 2007-2011 NTT DATA Corporation.
*******************************************************************************

��  �T�v�F

  ����readme�́ATERASOLUNA Batch Framework for Java��
  �@�\�T���v���v���W�F�N�g�𓱓�����菇���ł��B
  ���L�菇�ɏ]�����Ƃɂ��ATERASOLUNA�t���[�����[�N�̋@�\�T���v����
  ���s�����������邱�Ƃ��ł��܂��B

��  �O������F

  �J�����ɂ́A���炩���߉��L�̂��̂��p�ӂ���Ă���K�v������܂��B
  �܂����L����Ă���o�[�W���������ɓ���m�F���s�Ȃ��Ă��܂����A
  ���̃o�[�W�����ȊO�̊��œ���𐧌����邱�Ƃ��������̂ł͂���܂���B
  �܂��A�����̃C���X�g�[���y�ѐݒ�̎菇�ɂ��ẮA
  �ʓrWeb��̗��p�K�C�h�����Q�Ƃ��Ă��������B 

  �EJava 2 Runtime Environment Standard Edition 1.6
  �EEclipse SDK 3.4.2 
  �EPostgreSQL Database Server 8.4
    �܂���
  �EOracle11g
        
��  �v���W�F�N�g�̎��s�F

  �@JDBC�h���C�o�̔z�u
    PostgreSQL,Oracle��JDBC�h���C�o�͕t�����Ă��Ȃ��̂ŁA���p�Ҋe���ŕʓr���肷��K�v������܂��B
      �EPostgreSQL
          http://jdbc.postgresql.org/download.html
      �EOracle
          http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html
      ���t����bat�t�@�C������W���u���N������ꍇ�́A
        �擾�����h���C�o���ȉ��̃t�H���_�ɔz�u���Ă��������B
      �E�uterasoluna-batch-functionSample\lib�v
      
  �AZIP�t�@�C���̓W�J
    terasoluna-batch4j-funcsample_(�o�[�W�����ԍ�).zip���uC:\�v�����ɓW�J���܂��B
      �E��uC:\terasoluna-batch4j-funcsample_(�o�[�W�����ԍ�)\�v
       ���w�肳�ꂽ�f�B���N�g���͌Œ�ł͂Ȃ����߁A�K�X�ǂݑւ��Ď��s���Ă��������B
       �����ł́A�iWindows XP�́jC:\�ɓW�J����Ɖ��肵�A�菇��i�߂܂��B

  �B�f�[�^�x�[�X���̐ݒ�A������(�W���u���s�O�ɕK�����s����)
   ��PostgreSQL�̏ꍇ
    1.�O�����(���ɂ��ύX�\)
      pgAdmin���N�����A�V�����f�[�^�x�[�X���쐬����B
        ���O���functionsampledb
        �I�[�i�[���sample
        �G���R�[�f�B���O���UTF8
        Template����i�Ȃ��j
        �e�[�u����ԥ��pg_default

    2.�usetup_for_PostgreSQL.bat�v�̕ҏW
       �u/sql/postgresql/setup_for_PostgreSQL.bat�v����сu/sql/postgresql/init_job_control.bat�v��
        ���[���̊��ɍ������l�ɏ��������܂��B
        �ڍׂ́u/sql/postgresql/setup_for_PostgreSQL.bat�v���Q�Ƃ��Ă��������B

    3.�e�[�u���̍쐬
       �u/sql/postgresql/setup_for_PostgreSQL.bat�v�����s���܂��B(eclipse������s�s��)

   ��Oracle�̏ꍇ
    1.�O�����(���ɂ��ύX�\)
        �C���X�^���X�����XE
        DB���[�U�[��/�p�X���[�h���sample/sample

    2.�usetup_for_Oracle.bat�v�̕ҏW
        �u/sql/oracle/setup_for_Oracle.bat�v����сu/sql/oracle/init_job_control.bat�v��
        ���[���̊��ɍ������l�ɏ��������܂��B
        �ڍׂ́u/sql/oracle/setup_for_Oracle.bat�v���Q�Ƃ��Ă��������B

    3.�e�[�u���̍쐬
      �u/sql/oracle/setup_for_Oracle.bat�v�����s���܂��B(eclipse������s�s��)
      �uSQL> �v���\�����ꂽ�� exit�Ɠ��͂��ďI�����܂��B

  �CEclipse�ւ̃C���|�[�g
    �EEclipse��ʂɂāu�t�@�C�����C���|�[�g�v�����s���A
      �u��ʁ������v���W�F�N�g�����[�N�X�y�[�X�ցv��I�����u���ցv���N���b�N���܂��B
    �E�u���[�g�E�f�B���N�g���[�̑I���v�Ƀ`�F�b�N����������ԂŁu�Q�Ɓv���N���b�N���A
      �v���W�F�N�g���e�̃u���E�Y����A�œW�J�����f�B���N�g�����w�肵�܂��B
    �E�u�v���W�F�N�g�����[�N�X�y�[�X�փR�s�[�v�Ƀ`�F�b�N�������Ă��邱�Ƃ��m�F��A
      �u�I���v���N���b�N���܂��B
 
  �D���͗p�t�@�C���̔z�u�B
    �C���|�[�g�����v���W�F�N�g�ɑ��݂���u/input�v�t�H���_�̒��g��
    C:\temp\�ɔz�u���܂��B
    
  �EOracle���g�p����ꍇ�́A�ݒ�t�@�C���̏����������s��(Postgres���g�p����ꍇ�͕s�v)
    1.jdbc.properties�̓��e������������B
    �uconf\SqlMapAdminConfig\jdbc.properties�v�����
    �uconf\SqlMapConfig\jdbc.properties�v�̓��e���A�����ɍ��킹����������B
    
    2.SqlMapConfigMain.xml�̓��e������������B
      <sqlMap resource="SqlMapAdminConfig/BatchExecutor_Oracle.xml" />�̕�����
      �R�����g�A�E�g���O���āA
      <sqlMap resource="SqlMapAdminConfig/BatchExecutor_PostgreSQL.xml" />�̕�����
      �R�����g�A�E�g���Ă���
    
���W���u�̋N�����@

  script�t�H���_�z����bat�t�@�C������N�����܂��B

������m�F�p�T���v���̃W���u�ɂ���

    �{�T���v���̃W���u�ꗗ�����L�Ɏ����܂��B

    1. jp.terasoluna.batch.functionsample.b001
      �E�����^�W���u���s�@�\�A�r�W�l�X���W�b�N���s�@�\�A�g�����U�N�V�����Ǘ��@�\�̃T���v��
        �EB001001�F�uscripts/B001001.bat�v����N������
          AbstractTransactionBLogic���p�����t���[�����[�N����
          �g�����U�N�V�����Ǘ���C����(�f�[�^�͑S���ꊇ�ŃR�~�b�g����)
          �����I����́A���ׂẴf�[�^��familyName���u��؁v��
          firstName���u���Y�v�ɏ�����������B

        �EB001002�F�uscripts/B001002.bat�v����N������
          BLogic�C���^�t�F�[�X���p�����r�W�l�X���W�b�N����
          �g�����U�N�V�����̊Ǘ����s���T���v��(�f�[�^��10�����ƂɃR�~�b�g����)
          �����I����́A���ׂẴf�[�^��familyName���u��؁v��
          firstName���u���Y�v�ɏ�����������B

    2. jp.terasoluna.batch.functionsample.b002
      �E�񓯊��^�W���u�̃T���v��
        �EB002001BLogic�F�uscripts/B002001_forPostgreSQL.bat�v����N������
          (DB��Oracle���g�p���Ă���ꍇ�́uB002001_forOracle.bat�v����N��)
          �񓯊��^�W���u�̎��s�T���v���B
          �r�W�l�X���W�b�N�ł�Employee�e�[�u���̓��e��
          Employee2�e�[�u���ɃR�s�[����B

          �񓯊��^�W���u�G�O�[�L���[�^�[���I������ɂ�
          �uscripts/B002001_TERMINATE.bat�v�����s����

    3. jp.terasoluna.batch.functionsample.b003
      �E��O�n���h�����O�@�\�̃T���v��
        �EB003001BLogic�F�uscripts/B003001.bat�v����N������
          �W���u���s���Ƀr�W�l�X���W�b�N�ŗ�O�����������ꍇ�ɁA
          B003001ExceptionHandler�N���X�Ńn���h�����O���s���T���v��

    4. jp.terasoluna.batch.functionsample.b004
      �E�t�@�C������@�\�̃T���v��
        �EB004001BLogic�F�uscripts/B004001.bat�v����N������
          �uC:\\tmp\\input.csv�v�t�@�C�����uC:\\tmp\\outputB004001.csv�v��
          �R�s�[����T���v��
    
    5. jp.terasoluna.batch.functionsample.b005
      �E���b�Z�[�W�Ǘ��@�\�̃T���v��
        �EB005001BLogic�F�uscripts/B005001.bat�v����N������
          �umessages.properties�v�ɒ�`�������b�Z�[�W�𗘗p����
          ���O�o�͂��s���T���v��

    6. jp.terasoluna.batch.functionsample.b006
      �E�o�b�`�X�V�œK���@�\�̃T���v��
        �EB006001BLogic�F�uscripts/B006001.bat�v����N������
          �o�b�`�X�V�œK���@�\�𗘗p���āA������SQL���ꊇ�Ŕ��s����T���v���B
          �����ł�SQL�̔��s����insertData01:100��,deleteData01:100��,
          updateData01:100���̏��ɍœK��������ASQL�̔��s���s���Ă���B
          (���ۂɔ��s�������邽�߂ɂ́A�f�o�b�O�E�X�e�b�v�C���̕K�v������)
          
        �EB006002BLogic�F�uscripts/B006002.bat�v����N������
          �o�b�`�X�V�œK���@�\�𗘗p���āA������SQL���ꊇ�Ŕ��s����T���v���B
          ������ł́AComparator�C���^�t�F�[�X�����N���X��p�ӂ��A
          sort���\�b�h���Ăяo�����ƂŁA�œK�����SQL�̏���ύX���Ă���B
          �����ł�SQL�̔��s����updateData01:100��,deleteData09:100��,
          insertData99:100���̏��ɍœK��������ASQL�̔��s���s����B

    7. jp.terasoluna.batch.functionsample.b007
      �E���̓f�[�^�擾�@�\�̃T���v��
        �EB007001BLogic�F�uscripts/B007001.bat�v����N������
          �t�@�C��-DB�֘A�W���u
          �uC:\\tmp\\input.csv�v�̓��e��ǂݍ���ŁA
          Employee�e�[�u���Ƀf�[�^��}������T���v���B
          
        �EB007002BLogic�F�uscripts/B007002.bat�v����N������
          DB-�t�@�C���֘A�W���u
          �uEmployee�e�[�u���v�̓��e��ǂݍ���ŁA
          �uC:\\tmp\\outputB007002.csv�v�ɏo�͂���T���v��

    8. jp.terasoluna.batch.functionsample.b008
      �E�R���g���[���u���C�N�@�\�̃T���v��
        �EB008001BLogic�F�uscripts/B008001.bat�v����N������
          �R���g���[���u���C�N�@�\���g�p�����T���v���B
          �O�f�[�^�Ƃ̔�r�����ł͕����̃u���C�N�L�[���g�p���A
          �R���g���[���u���C�N�������Ƀ��O�ւ̃w�b�_�o�́A�s�������̃J�E���g���s���A
          ��f�[�^�Ƃ̔�r�����ł͒P��̃u���C�N�L�[��p���āA
          �R���g���[���u���C�N�̔����̍ۂɃo�b�`�X�V���s���B
          ---�uC:\\tmp\\KEN_ALL.CSV�v�̃f�[�^��ǂݍ��݁A
          ---�uZIP_CODE�v�e�[�u���ɍX�V����B

    9. jp.terasoluna.batch.functionsample.b009
      �E���̓f�[�^�擾�@�\�g�p���̓��̓`�F�b�N�@�\�A��O�n���h�����O�̃T���v��
        �EB009001BLogic�F�uscripts/B009001.bat�v����N������
          ���̓`�F�b�N�@�\�̃T���v���B
          �g�����̓`�F�b�N�G���[�n���h�����O�N���X��p�ӂ��AStatus�uSKIP�v��ԋp���Ă���B
          �r�W�l�X���W�b�N�ł́uC:\\tmp\\inputB009001.csv�v�t�@�C����ǂݍ���
          �uC:\\tmp\\outputB009001.csv�v�ɏo�͂���B
          ���̎��A2,11,16���ڂ̃f�[�^�œ��̓`�F�b�N�G���[���������A
          �o�̓t�@�C���ɏo�͂���Ȃ��B
          
        �EB009002BLogic�F�uscripts/B009002.bat�v����N������
          ���̓f�[�^�擾�@�\�g�p���̗�O�n���h�����O�T���v��
          �g����O�n���h�����O�N���X��p�ӂ��AStatus�uEND�v��ԋp���Ă���B
          �r�W�l�X���W�b�N�ł�Employee3�e�[�u����ǂݍ��݁A
          Employee2�e�[�u���ւ̏o�͂����݂邪�A
          2,7,12���ڂ̃f�[�^�œ��̓`�F�b�N��O���������A��������~����B
                         
���t�@�C�����o�͂���W���u�Ɋւ��āA�t�@�C���̍폜�����͓��ɋL�q���Ă��炸�A
  �t�@�C���o�͎��ɂ͏㏑���ƂȂ�悤�ݒ肵�Ă��܂��B

-------------------------------------------------------------------------------
Copyright 2007-2011 NTT DATA Corporation.
