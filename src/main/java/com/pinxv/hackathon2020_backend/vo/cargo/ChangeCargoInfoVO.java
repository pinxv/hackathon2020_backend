package com.pinxv.hackathon2020_backend.vo.cargo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author njuselhx
 */
@Data
public class ChangeCargoInfoVO {

    private String place;

    private Integer sum;

    private String batchNumber;

    private String description;

    private String destination;

    private String creator;

    private Timestamp timestamp;

}
