package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.repository.AdRepository;
import com.blueshit.joke.repository.JokeRepository;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/12/18.
 */
@Service
public class JokeServiceImpl implements JokeService {
    private static final Logger logger = LoggerFactory.getLogger(JokeServiceImpl.class);

    @Autowired
    JokeRepository jokeRepository;
    @Autowired
    AdRepository adRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 按照更新时间倒叙
     * @param type 0:首页 1:囧图 2:嗅事 3：内涵图
     */
    @Override
    public Page<Joke> getJokePages_byType(int type, int page) {
        StringBuffer hql = new StringBuffer("From Joke where status=2");
        if (type != 0) {
            hql.append(" and type=" + type);
        }else {
            hql.append(" and type not in (1,2,3)");
        }
        hql.append(" order by updateTime desc,up desc");
        return jokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 查询当前用户的笑话 按笑话状态分页
     * @param status 0:审核未通过 1:待审核 2:审核通过 3:全部
     * @return
     */
    @Override
    public Page<Joke> getJokePagesAll_byType(UserInfo userInfo, int status, int page) {
        StringBuffer hql = new StringBuffer("From Joke where userInfo.uid=" + userInfo.getUid());
        if (status != 3){
            hql.append(" and status="+status);
        }
        hql.append(" order by updateTime desc");
        return jokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 查询非此用户的笑话列表
     * @param uid 用户ID
     * @param number 查询条数
     * @return
     */
    @Override
    public List<Joke> getOtherUserJokeList(int uid, int number) {
        StringBuffer hql = new StringBuffer("From Joke WHERE style=1 AND userInfo.uid != "+uid);
        hql.append("ORDER BY updateTime DESC");
        return jokeRepository.findTopByHql(hql.toString(), number);
    }

    /**
     * 通过ID获取笑话内容
     * @param id
     * @param flag 标识：-1：前一条笑话 0：当前笑话 1:下一条笑话
     * @return
     */
    @Override
    public Joke getJokeById(int id,int flag){
        Joke joke = null;
        switch (flag){
            case -1:
                joke = jokeRepository.findOne(id-1 > 0 ? id-1 : 1);
                break;
            case 0:
                joke = jokeRepository.findOne(id);
                break;
            case 1:
                joke = jokeRepository.findOne(id + 1);
                break;
        }
        return joke;
    }

    /**
     * 通过UID获取笑话总数、笑话未通过总数
     * @param uid
     * @return
     */
    @Override
    public HashMap<String, Object> getJokeSumNumberById(int uid){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String sql_joke = "SELECT COUNT(*),IFNULL(SUM(IF(STATUS=2,1,0)),0) FROM joke WHERE uid="+uid;
        String sql_joke_vip = "SELECT COUNT(*),IFNULL(SUM(IF(STATUS=2,1,0)),0) FROM vipjoke WHERE uid="+uid;
        Query query = entityManager.createNativeQuery(sql_joke);
        List<Object[]> list = query.getResultList();
        Object[] obj = list.get(0);

        Query query_vip = entityManager.createNativeQuery(sql_joke_vip);
        List<Object[]> list_vip = query_vip.getResultList();
        Object[] obj_vip = list_vip.get(0);

        int sum = Integer.parseInt(obj[0].toString()) + Integer.parseInt(obj_vip[0].toString());
        int pass_sum = Integer.parseInt(obj[1].toString()) + Integer.parseInt(obj_vip[1].toString());
        map.put("jokeSum", sum);
        map.put("jokePassSum", pass_sum);
        float passRate = 0;
        if(Integer.parseInt(map.get("jokeSum").toString()) > 0){
            passRate = (float)(Integer.parseInt(map.get("jokePassSum").toString())) / (Integer.parseInt(map.get("jokeSum").toString())) *100;
        }
        map.put("passRate", passRate);
        return map;
    }

    /**
     * 保存普通笑话信息
     * @param joke
     * @return
     */
    @Override
    public boolean saveJoke(Joke joke){
        try{
            jokeRepository.save(joke);
        }catch (Exception ex){
            logger.error("保存普通笑话失败",ex);
            return false;
        }
        return true;
    }

    /**
     * 获取此笑话外的其它笑话
     * @param jid
     * @return
     */
    @Override
    public List<Joke> getOtherJokeList(int jid,int number){
        StringBuffer hql = new StringBuffer("From Joke WHERE style=1 AND status=2 AND jid != " + jid);
        hql.append("ORDER BY updateTime DESC");
        return jokeRepository.findTopByHql(hql.toString(), number);
    }

    /**
     * 根据笑话类型查询笑话信息
     * @param number 数据条数
     * @param style 1:图文 2:纯文字
     * @return
     */
    @Override
    public List<Joke> getTopJokeList(int number,int style){
        StringBuffer hql = new StringBuffer("From Joke WHERE style="+ style +" AND status=2");
        hql.append("ORDER BY updateTime DESC");
        return jokeRepository.findTopByHql(hql.toString(), number);
    }

    /**
     * 分页查询其它笑话
     * @param page 当前页
     * @param type 查询类型(1:文字笑话 2:笑点)
     * @param value 查询条件值
     * @return
     */
    public Page<Joke> getOtherJokePages(int page,int type,String value){
        StringBuffer hql = new StringBuffer("From Joke WHERE status=2 ");
        if(type == 1){
            hql.append(" AND style=2 ");
        }else{
            hql.append(" AND typeInfo.typeName='"+value+"'");
        }
        hql.append(" ORDER BY updateTime DESC ");
        return jokeRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 顶、踩笑话
     * @param jid 笑话ID
     * @param flag 1：顶 0：踩
     */
    public void upDownJoke(int jid,int flag){
        String hql ;
        if(flag == 1){
            hql = "UPDATE Joke SET up=up+1 WHERE jid="+jid;
        }else {
            hql = "UPDATE Joke SET down=down+1 WHERE jid="+jid;
        }
        jokeRepository.updateBySql(hql);
    }
}
