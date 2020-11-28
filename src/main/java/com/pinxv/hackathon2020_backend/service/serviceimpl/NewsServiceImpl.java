package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.NewsMapper;
import com.pinxv.hackathon2020_backend.entity.News;
import com.pinxv.hackathon2020_backend.service.NewsService;
import com.pinxv.hackathon2020_backend.task.NewsTask;
import com.pinxv.hackathon2020_backend.vo.NewsVO;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    NewsTask newsTask;

    /**
     *
     * @param highRiskAreaId
     * @return
     */
    @Override
    public ResponseVO getNews(Integer highRiskAreaId) {
        List<News> newsList = newsMapper.findAllByAreaid(highRiskAreaId);
        List<NewsVO> newsVOList =new ArrayList<>();
        for(News aNew : newsList){
            NewsVO newsVO = new NewsVO();
            newsVO.setTitle(aNew.getTitle());
            newsVO.setUrl(aNew.getUrl());
            newsVO.setDescription(aNew.getDescription());
            newsVOList.add(newsVO);
        }
        return ResponseVO.buildSuccess(newsList);
    }

    @Override
    public ResponseVO flushNews() {
        newsTask.executeNewsCrawler();
        return ResponseVO.buildSuccess();
    }
}
