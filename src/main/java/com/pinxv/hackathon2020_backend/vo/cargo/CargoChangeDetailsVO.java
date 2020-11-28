package com.pinxv.hackathon2020_backend.vo.cargo;

import lombok.Data;

import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Data
public class CargoChangeDetailsVO {
    String UUID;
    String description;
    List<ChangeCargoPlaceVO> placeList;
    List<UnsafeInfoVO> unsafeInfoVOList;

    public CargoChangeDetailsVO(String UUID, String description, List<ChangeCargoPlaceVO> placeList,List<UnsafeInfoVO> unsafeInfoVOList) {
        this.UUID = UUID;
        this.description = description;
        this.placeList = placeList;
        this.unsafeInfoVOList = unsafeInfoVOList;
    }
}
