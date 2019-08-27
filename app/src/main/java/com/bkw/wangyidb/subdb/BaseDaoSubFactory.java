package com.bkw.wangyidb.subdb;

import android.database.sqlite.SQLiteDatabase;

import com.bkw.wangyidb.db.BaseDao;
import com.bkw.wangyidb.db.BaseDaoFactory;

public class BaseDaoSubFactory extends BaseDaoFactory {
    private static final BaseDaoSubFactory INSTANCE = new BaseDaoSubFactory();

    public static BaseDaoSubFactory getInstance() {
        return INSTANCE;
    }


    /**
     * 定义一个用于实现分库的数据库对象
     */
    protected SQLiteDatabase subSqliteDatabase;

    /**
     * 生产basedao对象
     *
     * @param daoClass
     * @param entityClass
     * @param <T>
     * @param <M>
     * @return
     */
    @Override
    public <T extends BaseDao<M>, M> T getBaseDao(Class<T> daoClass, Class<M> entityClass) {
        BaseDao baseDao = null;
        if (map.get(PrivateDatabaseEnums.database.getValue()) != null) {
            return (T) map.get(PrivateDatabaseEnums.database.getValue());
        }
        subSqliteDatabase = SQLiteDatabase.openOrCreateDatabase(PrivateDatabaseEnums.database.getValue(), null);
        try {
            baseDao = daoClass.newInstance();
            baseDao.init(subSqliteDatabase, entityClass);

            map.put(PrivateDatabaseEnums.database.getValue(), baseDao);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }


}
