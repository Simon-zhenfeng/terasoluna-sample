@ECHO OFF

REM �C���X�g�[������jar�t�@�C���̖��O
SET FILE_NAME=ojdbc7.jar
REM �C���X�g�[������jar��groupId (�ύX�s�v)
SET GROUP_ID=com.oracle
REM �C���X�g�[������jar��artifactId (�t�@�C�����Ƒ�����)
SET ARTIFACT_ID=ojdbc7
REM �C���X�g�[������jar�̃o�[�W����
SET VERSION=12.1.0.1

REM �C���X�g�[��
CD /D %~DP0
CALL mvn install:install-file -Dfile=%FILE_NAME% -DgroupId=%GROUP_ID% -DartifactId=%ARTIFACT_ID% -Dversion=%VERSION% -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true

pause