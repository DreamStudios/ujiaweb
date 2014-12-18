package com.blueshit.joke.repository;

import com.blueshit.joke.entity.Joke;
import org.springframework.stereotype.Repository;

/**
 * 笑话DAO接口
 * Created by shulin on 2014/12/18.
 */
@Repository
public interface JokeRepository extends BaseRepository<Joke, Integer>{
}
