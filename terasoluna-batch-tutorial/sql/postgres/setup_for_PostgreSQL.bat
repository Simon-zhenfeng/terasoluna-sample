rem �g�p����DB���[�U�A�ڑ��������ύX����ꍇ�́A
rem ���L�L�q�������Q�l�ɂ��Ă��������B
rem �y�L�q�����z
rem psql -h <DB�T�[�oIP> -U <�g�p����DB���[�U> -d <�g�p����DB> -f terasoluna_tutorial_batch.sql
cd /d %~dp0
set path="C:\Program Files (x86)\PostgreSQL\8.4\bin\";%path%
psql -h 127.0.0.1 -U postgres -d terasoluna -f terasoluna_tutorial_batch.sql

pause