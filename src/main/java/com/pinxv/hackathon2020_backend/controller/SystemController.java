package com.pinxv.hackathon2020_backend.controller;

import com.pinxv.hackathon2020_backend.service.SystemService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author njuselhx
 */
@RestController
public class SystemController {

    @Autowired
    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/queryRiskLevel")
    public ResponseVO queryRiskLevel() {
        return this.systemService.queryRiskLevel();
    }

}
