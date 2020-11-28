package com.pinxv.hackathon2020_backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UnsafeCargoBatch")
@Data
public class UnsafeCargoBatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "batchNum", nullable = false)
    private String batchNum;

    @Column(name = "highRiskAreaId", nullable = false)
    private Integer highRiskAreaId;

    @Column(name = "unsafeChangeCargoId")
    private Integer unsafeChangeCargoId;

    @Column(name = "actualPlace")
    String actualPlace;

    @Column(name = "highRiskPlace")
    String highRiskPlace;

}
