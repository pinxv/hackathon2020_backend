package com.pinxv.hackathon2020_backend.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Entity
@Table(schema = "pinxv", name = "HighRiskArea")
@Data
public class HighRiskArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "riskLevelId")
    private Integer riskLevelId;

    /**
     * 风险地区名称
     */
    @Column(name = "area")
    private Integer area;

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

    /**
     * 风险等级
     */
    @Column(name = "riskLevel")
    private Integer riskLevel;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private RiskLevel riskLevelEntity;

}
