package com.pinxv.hackathon2020_backend.util;

import com.pinxv.hackathon2020_backend.vo.PositionInfoVO;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class GeographicalPositionUtil {


    public static PositionInfoVO getLatitudeAndLongitude(String locationName){
        return new PositionInfoVO(locationName, 0,0);
    }
}
