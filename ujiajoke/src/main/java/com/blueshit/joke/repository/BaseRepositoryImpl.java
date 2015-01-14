package com.blueshit.joke.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 202014/11/13 12:27
 * @description
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager em;

    // There are two constructors to choose from, either can be used.
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        // This is the recommended method for accessing inherited class dependencies.
        this.em = entityManager;
    }

    /**
     * 通过HQL查询list集合
     * @param hql
     * @return
     */
    @Override
    public List<T> whFindByList(String hql) {
        Query query = this.em.createQuery(hql);
        System.out.println("whFindByList>>>"+hql);
        return (List<T>) query.getResultList();
    }

    /**
     * 通过HQL查询Object对象
     * @param hql
     * @return
     */
    @Override
    public Object findByList(String hql) {
        Query query = this.em.createQuery(hql);
        System.out.println("findByList>>>"+hql);
        return (Object) query.getResultList();
    }

    /**
     * 根据HQL查询分页
     * @param hql
     * @param pageSize 每页显示数量
     * @param pageNo 页码
     * @return
     */
    @Override
    public Page<T> findByHql(String hql, int pageSize, int pageNo) {
        Page<T> page = null;
        Query query = em.createQuery(hql);
        int total = emHql(hql);
        // 设置查询结果的结束记录数
        int maxResults = pageSize;
        query.setMaxResults(maxResults);
        // 设置查询结果的开始记录数（从0开始计数）
        int firstResult = (pageNo - 1) * pageSize;
        query.setFirstResult(firstResult);
        List<T> rlist = query.getResultList();
        page = new PageImpl(rlist, new PageRequest(pageNo - 1, pageSize), total);

        return page;
    }

    /**
     * 根据HQL查询分页后的List值
     * @param hql
     * @param pageSize 每页显示数量
     * @param pageNo 页码
     * @return
     */
    public List<T> findPageListByHql(String hql, int pageSize, int pageNo){
        Query query = em.createQuery(hql);
        // 设置查询结果的结束记录数
        query.setMaxResults(pageSize);
        // 设置查询结果的开始记录数（从0开始计数）
        int firstResult = (pageNo - 1) * pageSize;
        query.setFirstResult(firstResult);
        return query.getResultList();
    }

    /**
     * 获取前几条数据
     * @param hql
     * @param count 数据量
     * @return
     */
    @Override
    public List<T> findTopByHql(String hql, int count) {
        Query query = em.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(count);
        return query.getResultList();
    }

    private int emHql(String hql) {
        int emNum = 0;
        Query query = em.createQuery(hql);
        emNum = query.getResultList().size();
        return emNum;
    }

    @Override
    public void updateBySql(String sql) {
        Query query = this.em.createQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void deleteBySql(String sql) {
        Query query = this.em.createQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<T> findListByPKs(ID[] pks) {
        Iterable<ID> pk = (Iterable<ID>) Arrays.asList(pks).iterator();
        List<T> list = findAll(pk);
        return list;
    }
}
