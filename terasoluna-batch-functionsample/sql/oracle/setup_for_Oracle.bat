@ECHO OFF
CD /D %~DP0

REM	�g�p����DB���[�U�A�p�X���[�h�A�ڑ��������ύX����ꍇ�́A
REM	���L�L�q�������Q�l�ɂ��Ă��������B
REM	�y�L�q�����z
REM	sqlplus <�g�p����DB���[�U>/<�p�X���[�h>@<�l�b�g�T�[�r�X��> @terasoluna_functionsample.sql

sqlplus sample/sample@SAMPLEDB @terasoluna_functionsample.sql

PAUSE