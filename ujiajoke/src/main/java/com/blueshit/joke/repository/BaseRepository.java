package com.blueshit.joke.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 202014/11/13 11:30
 * @description
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {

    /**
     * 通过HQL查询list集合
     * @param hql
     * @return
     */
    public List<T> whFindByList(String hql);

    /**
     * 通过HQL查询Object对象
     * @param hql
     * @return
     */
    public Object findByList(String hql);

    /**
     * 根据HQL查询分页
     * @param hql
     * @param pageSize 每页显示数量
     * @param pageNo 页码
     * @return
     */
    public Page<T> findByHql(String hql, int pageSize, int pageNo);

    /**
     * 获取前几条数据
     * @param hql
     * @param count 数据量
     * @return
     */
    public List<T> findTopByHql(String hql, int count);

    /**
     * 执行一修改的sql
     * @param sql
     */
    public void updateBySql(String sql);

    /**
     * 执行一个删除 sql
     * @param sql
     */
    public void deleteBySql(String sql);

    /**
     * 根据一组主键查询实体集合
     * @param pks
     * @return
     */
    public List<T> findListByPKs(ID[] pks);
}
