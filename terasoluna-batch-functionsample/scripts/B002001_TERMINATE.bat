@ECHO OFF
CD /D %~DP0

ECHO �I���t�@�C���쐬
TYPE NUL > C:\tmp\batch_terminate_file

ECHO 5�b�E�F�C�g
CALL sleep.bat 5

ECHO �I���t�@�C������
DEL C:\tmp\batch_terminate_file

PAUSE
