package com.lanou.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/17.
 */
public interface BaseDao<T> {
    /**
     * 保存某个对象
     * @param t 要保存的对象
     * @return 保存之后的对象
     * **/

    T save(T t);
    /**
     * 删除某个对象
     * @param t 要删除的对象 此对象必须是持久化状态的对象
     * **/

    void  delete(T t);
    /**
     * 更新某个对象
     * @param t 要更新的对象**/
    void update(T t);

    /**
     * 查询主键对应id参数的对象
     * @param c 要查询的类
     * @param id 主键id **/
    T get(Class<T>c,Serializable id);

    /**
     * 根据hql条件语句返回第一个符合的对象
     * @param hql 查询语句
     * @param params 查询语句对应的参数列表**/
    T find(String hql, Map<String,Object>params);

    /**根据hql条件语句返回符合的结果集
     * @param hql 查询语句
     * @param params 查询语句对应的参数列表**/
    List<T> findAll(String hql,Map<String,Object>params);


}
