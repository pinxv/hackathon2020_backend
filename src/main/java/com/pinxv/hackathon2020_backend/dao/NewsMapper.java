package com.pinxv.hackathon2020_backend.dao;

import com.pinxv.hackathon2020_backend.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>description: 新闻数据库接口</p>
 *
 * @date 2020/11/28
 */
public interface NewsMapper  extends CrudRepository<News, Integer> {

    /**
     * 根据areaid获取新闻
     * @return
     */
    List<News> findAllByAreaid(Integer id);

}
