@ECHO OFF
CD /D %~DP0

REM �g�p����DB���[�U�A�p�X���[�h�A�ڑ��������ύX����ꍇ�́A
REM ���L�L�q�������Q�l�ɂ��Ă��������B
REM �y�L�q�����z
REM psql -h <DB�T�[�oIP> -U <�g�p����DB���[�U> -d <�g�p����DB> -f terasoluna_functionsample.sql

SET PATH=C:\Program Files\PostgreSQL\9.3\bin\;%PATH%
psql -h 127.0.0.1 -U postgres -d functionsampledb -f terasoluna_functionsample.sql

PAUSE