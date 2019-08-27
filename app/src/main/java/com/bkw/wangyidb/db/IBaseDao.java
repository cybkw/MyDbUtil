package com.bkw.wangyidb.db;

import java.util.List;

/**
 * @param <T> 实体对象
 */
public interface IBaseDao<T> {
    /**
     * 插入数据
     *
     * @param entity 目标对象
     * @return
     */
    long insert(T entity);

    /**
     * 修改数据
     *
     * @param entity 目标对象
     * @param where  修改对象
     * @return
     */
    long update(T entity, T where);

    /**
     * 删除数据
     *
     * @param where 目标对象
     * @return
     */
    int delete(T where);

    /**
     * 查询 返回一个集合
     *
     * @param where 查询条件
     * @return
     */
    List<T> query(T where);

    /**
     * 查询 返回一个集合
     *
     * @param where 查询条件
     * @return
     */
    List<T> query(T where, String orderBy, Integer startIndex, Integer limit);

}
