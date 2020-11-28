package com.pinxv.hackathon2020_backend.task;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.pinxv.hackathon2020_backend.dao.CargoBatchMapper;
import com.pinxv.hackathon2020_backend.dao.ChangeCargoInfoMapper;
import com.pinxv.hackathon2020_backend.dao.HighRiskAreaMapper;
import com.pinxv.hackathon2020_backend.dao.UnsafeCargoBatchMapper;
import com.pinxv.hackathon2020_backend.entity.CargoBatch;
import com.pinxv.hackathon2020_backend.entity.ChangeCargoInfo;
import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import com.pinxv.hackathon2020_backend.entity.UnsafeCargoBatch;
import com.pinxv.hackathon2020_backend.util.GeographicalPositionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Component
public class UnsafePlaceUpdateTask {

    @Autowired
    ChangeCargoInfoMapper changeCargoInfoMapper;

    @Autowired
    HighRiskAreaMapper highRiskAreaMapper;

    @Autowired
    UnsafeCargoBatchMapper unsafeCargoBatchMapper;

    @Autowired
    CargoBatchMapper cargoBatchMapper;

    Long maxHourBetween = 48L;
    Double maxDistanceBetween = 200.0;

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void updateUnsafePlace() {
        unsafeCargoBatchMapper.deleteAll();
        List<ChangeCargoInfo> changeCargoInfoList = changeCargoInfoMapper.findAll();
        for (ChangeCargoInfo changeCargoInfo : changeCargoInfoList) {
            updateUnsafePlace(changeCargoInfo);
        }
    }

    public void updateUnsafePlace(ChangeCargoInfo changeCargoInfo) {
        Date now = DateUtil.parse(DateUtil.now());
        List<HighRiskArea> highRiskAreaList = highRiskAreaMapper.findAll();
        for (HighRiskArea highRiskArea : highRiskAreaList) {
            Date cargoDate = DateUtil.parse(changeCargoInfo.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            long timeBetween = DateUtil.between(cargoDate, now, DateUnit.HOUR);
            if (timeBetween < maxHourBetween) {
                double distanceBetween = GeographicalPositionUtil.getDistance(highRiskArea.getArea(), changeCargoInfo.getPlace());
                if (distanceBetween < maxDistanceBetween) {
                    UnsafeCargoBatch unsafeCargoBatch = new UnsafeCargoBatch();
                    unsafeCargoBatch.setBatchNum(changeCargoInfo.getBatchNumber());
                    unsafeCargoBatch.setHighRiskAreaId(highRiskArea.getId());
                    unsafeCargoBatch.setUnsafeChangeCargoId(changeCargoInfo.getId());
                    unsafeCargoBatch.setActualPlace(changeCargoInfo.getPlace());
                    unsafeCargoBatch.setHighRiskPlace(highRiskArea.getArea());
                    unsafeCargoBatchMapper.save(unsafeCargoBatch);
                    List<CargoBatch> cargoBatchList = cargoBatchMapper.findByBatchNumber(changeCargoInfo.getBatchNumber());
                    if (cargoBatchList.isEmpty()) {
                        return;
                    } else {
                        CargoBatch cargoBatch = cargoBatchList.get(0);
                        cargoBatch.setIsSafe(false);
                        cargoBatchMapper.save(cargoBatch);
                    }
                }
            }
        }

    }
}
