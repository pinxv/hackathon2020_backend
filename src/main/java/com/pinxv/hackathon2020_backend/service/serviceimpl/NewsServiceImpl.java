package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.HighRiskAreaMapper;
import com.pinxv.hackathon2020_backend.dao.NewsMapper;
import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import com.pinxv.hackathon2020_backend.entity.News;
import com.pinxv.hackathon2020_backend.selenium.NewsCrawler;
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

    @Autowired
    HighRiskAreaMapper highRiskAreaMapper;

    /**
     * @param highRiskAreaId
     * @return
     */
    @Override
    public ResponseVO getNews(Integer highRiskAreaId) {
        List<News> newsList = newsMapper.findAllByAreaid(highRiskAreaId);
        List<NewsVO> newsVOList = new ArrayList<>();
        for (News aNew : newsList) {
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

        List<HighRiskArea> highRiskAreas = (List<HighRiskArea>) highRiskAreaMapper.findAll();
        for (HighRiskArea highRiskArea : highRiskAreas) {
            //根据area名称爬取十条数据
            List<NewsVO> newsVOS = NewsCrawler.crawl(highRiskArea.getArea());

            //删除过时新闻
            List<News> news = newsMapper.findAllByAreaid(highRiskArea.getId());
            for (News aNew : news) {
                newsMapper.delete(aNew);
            }

            //添加新的新闻
            for (NewsVO newsVO : newsVOS) {
                News news1 = new News();
                news1.setAreaid(highRiskArea.getId());
                news1.setTitle(newsVO.getTitle());
                news1.setDescription(newsVO.getDescription());
                news1.setUrl(newsVO.getUrl());
                newsMapper.save(news1);
            }

        }
        return ResponseVO.buildSuccess();
    }
}
