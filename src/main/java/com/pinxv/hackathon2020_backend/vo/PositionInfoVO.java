package com.pinxv.hackathon2020_backend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionInfoVO {

    /**
     * 地点名称
     */
    private String positionName;

    /**
     * 经度
     */
    private Float longitude;

    /**
     * 纬度
     */
    private Float latitude;

    /**
     * 地理位置编码
     */
    private String adcode;

}
