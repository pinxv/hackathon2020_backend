package com.pinxv.hackathon2020_backend.vo.cargo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.pinxv.hackathon2020_backend.entity.ChangeCargoInfo;
import com.pinxv.hackathon2020_backend.util.GeographicalPositionUtil;
import com.pinxv.hackathon2020_backend.vo.PositionInfoVO;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class ChangeCargoPlaceVO {
    String place;
    String time;
    Float longitude;
    Float latitude;

    public ChangeCargoPlaceVO(ChangeCargoInfo changeCargoInfo) {
        this.place = changeCargoInfo.getPlace();
        this.time = DateUtil.date(changeCargoInfo.getTimestamp().getTime()).toString("yyyy-MM-dd HH:mm:ss");
        PositionInfoVO positionInfoVO = GeographicalPositionUtil.getPositionInfo(changeCargoInfo.getPlace());
        this.longitude = positionInfoVO.getLongitude();
        this.latitude = positionInfoVO.getLatitude();
    }
}
