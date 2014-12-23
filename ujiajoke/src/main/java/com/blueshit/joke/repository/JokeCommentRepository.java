package com.blueshit.joke.repository;

import com.blueshit.joke.entity.JokeComment;
import org.springframework.stereotype.Repository;

/**
 * 笑话评论接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/23 14:16
 * @description
 */
@Repository
public interface JokeCommentRepository extends BaseRepository<JokeComment, Integer>{
}
