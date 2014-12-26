package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.VipJokeComment;
import com.blueshit.joke.repository.VipJokeCommentRepository;
import com.blueshit.joke.service.VipJokeCommentService;
import com.blueshit.joke.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VIP笑话评论接口实现类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 14:28
 * @description
 */
@Service
public class VipJokeCommentServiceImpl implements VipJokeCommentService {
    private static final Logger logger = LoggerFactory.getLogger(VipJokeCommentServiceImpl.class);

    private VipJokeCommentRepository vipJokeCommentRepository;

    @Autowired
    public VipJokeCommentServiceImpl(VipJokeCommentRepository jokeCommentRepository) {
        this.vipJokeCommentRepository = jokeCommentRepository;
    }

    /**
     * 根据笑话ID查询笑话评论内容
     * @param jid 笑话ID
     * @param page 页码
     * @return
     */
    @Override
    public Page<VipJokeComment> getPageJokeCommentByJid(int jid, int page) {
        StringBuffer hql = new StringBuffer("From VipJokeComment WHERE joke.jid=" + jid);
        hql.append(" order by createTime desc");
        return vipJokeCommentRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
    }

    /**
     * 判断该笑话是否被此用户评论过
     * @param uid
     * @param jid
     * @return
     */
    @Override
    public boolean isComment(int uid,int jid){
        try {
            String hql = " FROM VipJokeComment WHERE joke.jid=" + jid + " AND userInfo.uid=" + uid;
            List<VipJokeComment> list = vipJokeCommentRepository.findTopByHql(hql, 1);
            return list.size() > 0;
        }catch (Exception ex){
            logger.error("获取用户是否评论失败",ex);
        }
        return false;
    }

    /**
     * 添加评论
     * @param jokeComment
     * @return
     */
    @Override
    public boolean addJokeComment(VipJokeComment jokeComment){
        try{
            vipJokeCommentRepository.save(jokeComment);
        }catch (Exception ex){
            logger.error("添加评论失败",ex);
            return false;
        }
        return true;
    }

    /**
     * 获取笑话评论内容
     * @param jid 笑话ID
     * @Param number 数据条数
     * @return
     */
    public List<VipJokeComment> getJokeCommentList(int jid,int number){
        List<VipJokeComment> list = null;
        try {
            StringBuffer hql = new StringBuffer("From JokeComment WHERE joke.jid=" + jid);
            hql.append(" order by createTime desc");
            list = vipJokeCommentRepository.findTopByHql(hql.toString(), number);
        }catch (Exception ex){
            logger.info("异步获取评论列表失败",ex);
        }
        return list;
    }
}
