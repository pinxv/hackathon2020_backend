package com.pinxv.hackathon2020_backend.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fengguohao
 */
@Entity
@Data
@Table(schema = "pinxv", name = "RiskLevel")
public class RiskLevel implements Serializable {

    private static final long serialVersionUID = -2472247564586988692L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "block", nullable = false)
    private String block;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Float latitude;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "riskLevel")
    private Integer riskLevel;

}
