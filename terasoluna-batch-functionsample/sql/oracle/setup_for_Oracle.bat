@ECHO OFF
CD /D %~DP0

rem	�g�p����DB���[�U�A�p�X���[�h�A�ڑ��������ύX����ꍇ�́A
rem	���L�L�q�������Q�l�ɂ��Ă��������B
rem	�y�L�q�����z
rem	sqlplus <�g�p����DB���[�U>/<�p�X���[�h>@<�l�b�g�T�[�r�X��> @terasoluna_functionsample.sql

sqlplus sample/sample@SAMPLEDB @terasoluna_functionsample.sql

pause