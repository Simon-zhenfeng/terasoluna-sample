rem	�g�p����DB���[�U�A�p�X���[�h�A�ڑ��������ύX����ꍇ�́A
rem	���L�L�q�������Q�l�ɂ��Ă��������B
rem	�y�L�q�����z
rem	sqlplus <�g�p����DB���[�U>/<�p�X���[�h>@<�l�b�g�T�[�r�X��> @terasoluna_functionsample_thin.sql

sqlplus functest/functest@10.68.255.230:1521/TERADB @create_table_dbmessage.sql
sqlplus functest/functest@10.68.255.230:1521/TERADB @create_table_job_control.sql
sqlplus functest/functest@10.68.255.230:1521/TERADB @insert_dbmessage.sql
sqlplus functest/functest@10.68.255.230:1521/TERADB @insert_job_control.sql

pause