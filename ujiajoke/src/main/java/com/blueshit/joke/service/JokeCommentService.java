package com.blueshit.joke.service;

import com.blueshit.joke.entity.JokeComment;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 笑话评论接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 14:28
 * @description
 */
public interface JokeCommentService {

    /**
     * 根据笑话ID查询笑话评论内容
     * @param jid 笑话ID
     * @param page 页码
     * @return
     */
    public Page<JokeComment> getPageJokeCommentByJid(int jid,int page);

    /**
     * 判断该笑话是否被此用户评论过
     * @param uid
     * @param jid
     * @return
     */
    public boolean isComment(int uid,int jid);

    /**
     * 添加评论
     * @param jokeComment
     * @return
     */
    public boolean addJokeComment(JokeComment jokeComment);

    /**
     * 获取笑话评论内容
     * @param jid 笑话ID
     * @Param number 数据条数
     * @return
     */
    public List<JokeComment> getJokeCommentList(int jid,int number);
}
