REM �g�p����DB���[�U�A�ڑ��������ύX����ꍇ�́A
REM ���L�L�q�������Q�l�ɂ��Ă��������B
REM �y�L�q�����z
REM psql -h <DB�T�[�oIP> -U <�g�p����DB���[�U> -d <�g�p����DB> -f terasoluna_tutorial_batch.sql
CD /d %~dp0
SET PATH="C:\Program Files\PostgreSQL\9.3\bin\";%PATH%
psql -h 127.0.0.1 -U postgres -d terasoluna -f terasoluna_tutorial_batch.sql

PAUSE