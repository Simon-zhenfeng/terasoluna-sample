package jp.terasoluna.batch.functionsample.b007.b007002;

import org.apache.ibatis.session.ResultHandler;

import jp.terasoluna.batch.functionsample.b007.CsvRecord;

public interface B007002Dao {
    /**
     * EMPLOYEEテーブルのデータを取得する。
     * @param object SQLパラメータ引数オブジェクト
     * @param handler ResultHandler
     */
    void collectEmployee(Object object, ResultHandler<CsvRecord> handler);
}
