package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.JokeComment;
import com.blueshit.joke.repository.JokeCommentRepository;
import com.blueshit.joke.service.JokeCommentService;
import com.blueshit.joke.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 笑话评论接口实现类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 14:28
 * @description
 */
@Service
public class JokeCommentServiceImpl implements JokeCommentService {
    private static final Logger logger = LoggerFactory.getLogger(JokeCommentServiceImpl.class);

    private JokeCommentRepository jokeCommentRepository;

    @Autowired
    public JokeCommentServiceImpl(JokeCommentRepository jokeCommentRepository) {
        this.jokeCommentRepository = jokeCommentRepository;
    }

    /**
     * 根据笑话ID查询笑话评论内容
     * @param jid 笑话ID
     * @param page 页码
     * @return
     */
    @Override
    public Page<JokeComment> getPageJokeCommentByJid(int jid, int page) {
        StringBuffer hql = new StringBuffer("From JokeComment WHERE joke.jid=" + jid);
        hql.append(" order by createTime desc");
        return jokeCommentRepository.findByHql(hql.toString(), Constants.Common.PAGE_SIZE, page);
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
            String hql = " FROM JokeComment WHERE joke.jid=" + jid + " AND userInfo.uid=" + uid;
            List<JokeComment> list = jokeCommentRepository.findTopByHql(hql, 1);
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
    public boolean addJokeComment(JokeComment jokeComment){
        try{
            jokeCommentRepository.save(jokeComment);
        }catch (Exception ex){
            logger.error("添加评论失败",ex);
            return false;
        }
        return true;
    }
}
