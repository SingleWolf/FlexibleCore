package com.walker.core.data.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author Walker
 * @date on 2018/4/11 0011 下午 18:11
 * @email feitianwumu@163.com
 * @desc 数据库处理接口
 */
public interface OnDatabaseTransact {
    void onCreate(SQLiteDatabase db);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
