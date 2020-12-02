package com.pinxv.hackathon2020_backend.task;

import cn.hutool.core.util.RuntimeUtil;
import com.pinxv.hackathon2020_backend.dao.HighRiskAreaMapper;
import com.pinxv.hackathon2020_backend.dao.NewsMapper;
import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import com.pinxv.hackathon2020_backend.entity.News;
import com.pinxv.hackathon2020_backend.selenium.NewsCrawler;
import com.pinxv.hackathon2020_backend.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Component
public class NewsTask {
    @Autowired
    NewsMapper newsMapper;

    @Autowired
    HighRiskAreaMapper highRiskAreaMapper;

    //@Scheduled(cron = "0 0/15 * * * ?")
    public void executeNewsCrawler() {
        List<HighRiskArea> highRiskAreas = (List<HighRiskArea>) highRiskAreaMapper.findAll();
        for (HighRiskArea highRiskArea : highRiskAreas) {
            // 根据area名称爬取十条数据
            List<NewsVO> newsVOS = NewsCrawler.crawl(highRiskArea.getArea());
            // 删除过时新闻
            List<News> news = newsMapper.findAllByAreaid(highRiskArea.getId());
            for (News aNew : news) {
                newsMapper.delete(aNew);
            }
            // 添加新的新闻
            for (NewsVO newsVO : newsVOS) {
                News news1 = new News();
                news1.setAreaid(highRiskArea.getId());
                news1.setTitle(newsVO.getTitle());
                news1.setDescription(newsVO.getDescription());
                news1.setUrl(newsVO.getUrl());
                newsMapper.save(news1);
            }

        }
        RuntimeUtil.exec("killall chromedriver");
    }

}
