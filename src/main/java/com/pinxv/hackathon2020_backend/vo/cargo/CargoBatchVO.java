package com.pinxv.hackathon2020_backend.vo.cargo;

import lombok.Data;

/**
 * @author njuselhx
 */
@Data
public class CargoBatchVO {

    private String batchNumber;

    private String description;

    private Integer sum;

    private String creator;

    private Boolean isSafe;

    private String destination;

    private String place;

}
