package com.pinxv.hackathon2020_backend.service;

import com.pinxv.hackathon2020_backend.vo.ResponseVO;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public interface NewsService {
    ResponseVO getNews(Integer highRiskAreaId);

    ResponseVO flushNews();
}
