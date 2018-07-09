package com.walker.core.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

/**
 * @author Walker
 * @date on 2018/4/11 0011 下午 17:44
 * @email feitianwumu@163.com
 * @desc 数据库管理者
 */

public class DatabaseManager extends SQLiteOpenHelper {
    /**
     * 数据库管理实例
     */
    private static volatile DatabaseManager sInstance;
    /**
     * 数据库处理实现接口
     */
    private static OnDatabaseTransact sTransactImpl;
    /**
     * 数据库存储路径
     */
    private static String sDbPath;
    /**
     * 数据库版本
     */
    private static int sDbVersion;

    /**
     * 初始化
     *
     * @param transact  处理接口
     * @param dbPath    数据库存储路径
     * @param dbVersion 数据库版本
     */
    public static void init(OnDatabaseTransact transact, String dbPath, int dbVersion) {
        sTransactImpl = transact;
        sDbPath = dbPath;
        sDbVersion = dbVersion;
    }

    /**
     * 通过单例模式双锁获取数据库实例
     *
     * @param context 上下文
     * @return 数据库管理实例
     */
    public static synchronized DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DatabaseManager.class) {
                if (sInstance == null) {
                    if (TextUtils.isEmpty(sDbPath)) {
                        throw new RuntimeException("can not create db with sDbPath is null");
                    }
                    if (sDbVersion < 1) {
                        throw new RuntimeException("can not create db with sDbVersion < 1");
                    }
                    sInstance = new DatabaseManager(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 注销数据库实例
     */
    public static synchronized void destory() {
        if (sInstance != null) {
            sInstance.close();
        }
    }

    /**
     * 数据库管理的构造方法
     *
     * @param context 上下文
     */
    public DatabaseManager(Context context) {
        super(context, sDbPath, null, sDbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (sTransactImpl != null) {
            sTransactImpl.onCreate(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (sTransactImpl != null) {
            sTransactImpl.onUpgrade(db, oldVersion, newVersion);
        }
    }
}
