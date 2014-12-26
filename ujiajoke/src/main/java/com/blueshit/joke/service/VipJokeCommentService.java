package com.blueshit.joke.service;

import com.blueshit.joke.entity.VipJokeComment;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * VIP笑话评论接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 14:28
 * @description
 */
public interface VipJokeCommentService {

    /**
     * 根据笑话ID查询笑话评论内容
     * @param jid 笑话ID
     * @param page 页码
     * @return
     */
    public Page<VipJokeComment> getPageJokeCommentByJid(int jid, int page);

    /**
     * 判断该笑话是否被此用户评论过
     * @param uid
     * @param jid
     * @return
     */
    public boolean isComment(int uid, int jid);

    /**
     * 添加评论
     * @param vipJokeComment
     * @return
     */
    public boolean addJokeComment(VipJokeComment vipJokeComment);

    /**
     * 获取笑话评论内容
     * @param jid 笑话ID
     * @Param number 数据条数
     * @return
     */
    public List<VipJokeComment> getJokeCommentList(int jid, int number);
}
