package com.pinxv.hackathon2020_backend.controller;

import com.pinxv.hackathon2020_backend.service.NewsService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("")
    public ResponseVO getNews(int highRiskAreaId){
        return newsService.getNews(highRiskAreaId);
    }

    @PostMapping("/flush")
    public ResponseVO flushNews(){
        return newsService.flushNews();
    }
}
