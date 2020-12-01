package com.pinxv.hackathon2020_backend.task;

import cn.hutool.core.util.RuntimeUtil;
import com.pinxv.hackathon2020_backend.dao.HighRiskAreaMapper;
import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import com.pinxv.hackathon2020_backend.selenium.RiskLevelCrawler;
import com.pinxv.hackathon2020_backend.util.GeographicalPositionUtil;
import com.pinxv.hackathon2020_backend.vo.PositionInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description: </p>
 *
 * @author njuselhx
 * @date 2020/11/28
 */
@Component
public class RiskLevelTask {

    @Autowired
    private final HighRiskAreaMapper highRiskAreaMapper;

    public RiskLevelTask(HighRiskAreaMapper highRiskAreaMapper) {
        this.highRiskAreaMapper = highRiskAreaMapper;
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void executeRiskLevelCrawler() {
        List<Pair<String, Integer>> riskLevels = RiskLevelCrawler.crawl();
        List<PositionInfoVO> positionInfos = new ArrayList<>();
        for (Pair<String, Integer> riskLevel : riskLevels) {
            positionInfos.add(GeographicalPositionUtil.getPositionInfo(riskLevel.getFirst()));
        }
        for (int i = 0; i < positionInfos.size(); i++) {
            List<HighRiskArea> highRiskAreas = this.highRiskAreaMapper.findByArea(positionInfos.get(i).getPositionName());
            HighRiskArea highRiskArea;
            if (highRiskAreas.isEmpty()) {
                highRiskArea = new HighRiskArea();
            } else {
                highRiskArea = highRiskAreas.get(0);
            }
            highRiskArea.setArea(positionInfos.get(i).getPositionName());
            highRiskArea.setAdcode(positionInfos.get(i).getAdcode());
            highRiskArea.setLatitude(positionInfos.get(i).getLatitude());
            highRiskArea.setLongitude(positionInfos.get(i).getLongitude());
            highRiskArea.setRiskLevel(riskLevels.get(i).getSecond());
            this.highRiskAreaMapper.save(highRiskArea);
        }
        RuntimeUtil.exec("killall chromedriver");
    }

}
