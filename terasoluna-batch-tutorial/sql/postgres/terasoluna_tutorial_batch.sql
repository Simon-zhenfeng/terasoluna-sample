-- 対象DBMS    :PostgreSQL 9.3

-- 文字コード設定
SET client_encoding = 'UTF8';

-- シーケンス削除
\i drop_all_sequence.sql

-- テーブル削除
\i drop_all_tables.sql

-- シーケンス生成
\i create_sequence_job_control.sql

-- テーブル生成
\i create_table_job_control.sql
\i create_table_nyusyukkin.sql

-- データ挿入
\i insert_all_data.sql

