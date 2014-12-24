package com.blueshit.joke.repository;

import com.blueshit.joke.entity.Ad;
import org.springframework.stereotype.Repository;

/**
 * 广告持久操作层
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 202014/11/13 11:30
 * @description
 */
@Repository
public interface AdRepository extends BaseRepository<Ad, Integer>{
}
