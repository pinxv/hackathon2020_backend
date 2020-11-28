package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.HighRiskAreaMapper;
import com.pinxv.hackathon2020_backend.dao.RiskLevelMapper;
import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import com.pinxv.hackathon2020_backend.enums.RiskLevelCode;
import com.pinxv.hackathon2020_backend.service.SystemService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author njuselhx
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private final RiskLevelMapper riskLevelMapper;

    @Autowired
    private final HighRiskAreaMapper highRiskAreaMapper;

    public SystemServiceImpl(RiskLevelMapper riskLevelMapper, HighRiskAreaMapper highRiskAreaMapper) {
        this.riskLevelMapper = riskLevelMapper;
        this.highRiskAreaMapper = highRiskAreaMapper;
    }

    @Override
    public ResponseVO queryRiskLevel() {
        List<HighRiskArea> medium = highRiskAreaMapper.findByRiskLevel(RiskLevelCode.MEDIUM.getCode());
        List<HighRiskArea> high = highRiskAreaMapper.findByRiskLevel(RiskLevelCode.HIGH.getCode());
        return null;
    }

}
