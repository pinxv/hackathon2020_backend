package com.pinxv.hackathon2020_backend.vo.risklevel;

import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import lombok.Data;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Data
public class HighRiskLevelVO {
    Integer id;
    String adcode;
    String locationName;
    Float longitude;
    Float latitude;
    Integer riskLevel;

    HighRiskLevelVO() {
    }

    public HighRiskLevelVO(HighRiskArea area) {
        this.setId(area.getId());
        this.setAdcode(area.getAdcode());
        this.setLocationName(area.getArea());
        this.setLongitude(area.getLongitude());
        this.setLatitude(area.getLatitude());
        this.setRiskLevel(area.getRiskLevel());
    }
}
