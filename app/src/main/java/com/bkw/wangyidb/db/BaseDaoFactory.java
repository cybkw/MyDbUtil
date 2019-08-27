package com.bkw.wangyidb.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseDaoFactory {

    /**
     * 饿汉式单例
     */
    private static final BaseDaoFactory instance = new BaseDaoFactory();

    private SQLiteDatabase sqLiteDatabase;
    private String sqlitePath;

    /**
     * 由外部传递一个包名进来
     */
    public static final String PACKAGENAME = "com.bkw.wangyidb/";

    /**
     * 数据库名
     */
    private static final String DB_NAME = "wydb.db";

    public static BaseDaoFactory getInstance() {
        return instance;
    }

    /**
     * 设计数据库连接池，new 容器，只要new一次，就不再创建。考虑多线程的问题
     */
    protected Map<String, BaseDao> map = Collections.synchronizedMap(new HashMap<String, BaseDao>());

    protected BaseDaoFactory() {
        sqlitePath = "data/data/" + PACKAGENAME + DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqlitePath, null);
        Log.e("TAG", "数据库已打开");
    }

    /**
     * 生成BaseDao对象
     */
    // 生产basedao对象
    public <T extends BaseDao<M>, M> T getBaseDao(Class<T> daoClass, Class<M> entityClass) {
        BaseDao baseDao = null;
        if (map.get(daoClass.getSimpleName()) != null) {
            return (T) map.get(daoClass.getSimpleName());
        }
        try {
            baseDao = daoClass.newInstance();
            baseDao.init(sqLiteDatabase, entityClass);
            map.put(daoClass.getSimpleName(), baseDao);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }


}
