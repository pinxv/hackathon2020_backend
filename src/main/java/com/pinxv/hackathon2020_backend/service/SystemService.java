package com.pinxv.hackathon2020_backend.service;

import com.pinxv.hackathon2020_backend.vo.ResponseVO;

/**
 * @author njuselhx
 */
public interface SystemService {

    /**
     * get risk level from database
     *
     * @return response view object
     */
    ResponseVO queryRiskLevel();

}
