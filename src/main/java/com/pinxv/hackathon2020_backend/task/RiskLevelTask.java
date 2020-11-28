package com.pinxv.hackathon2020_backend.task;

import com.pinxv.hackathon2020_backend.dao.RiskLevelMapper;
import com.pinxv.hackathon2020_backend.selenium.RiskLevelCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Component
public class RiskLevelTask {

    @Autowired
    private final RiskLevelMapper riskLevelMapper;

    public RiskLevelTask(RiskLevelMapper riskLevelMapper) {
        this.riskLevelMapper = riskLevelMapper;
    }

    @Scheduled(cron = "")
    public void executeRiskLevelCrawler() throws InterruptedException {
        List<Pair<String, Integer>> riskLevels = RiskLevelCrawler.crawl();

    }

}
