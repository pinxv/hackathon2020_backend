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

    public CargoChangeDetailsVO(String UUID,String description,List<ChangeCargoPlaceVO> placeList){
        this.UUID = UUID;
        this.description = description;
        this.placeList = placeList;
    }
}
