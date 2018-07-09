package com.walker.core.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Walker
 * @date on 2018/4/11 0011 下午 18:35
 * @email feitianwumu@163.com
 * @desc 数据库操作类，处理有关sqlite相关的增删改查
 */
public abstract class BaseDB implements OnDatabaseTransact {
    /**
     * 数据库管理实例
     */
    private DatabaseManager mDBManager;
    /**
     * 数据库操作者
     */
    private SQLiteDatabase mDB;

    public BaseDB(Context context) {
        DatabaseManager.init(this, setDbPath(), setDbVersion());
        mDBManager = DatabaseManager.getInstance(context);
        mDB = mDBManager.getWritableDatabase();
    }

    protected abstract String setDbPath();


    protected abstract int setDbVersion();

    /**
     * 关闭数据库
     */
    public void close() {
        if (mDBManager != null) {
            DatabaseManager.destory();
        }
    }

    /**
     * 获取DB
     *
     * @return SQLiteDatabase
     */
    public SQLiteDatabase getExecDB() {
        return mDB;
    }
}
