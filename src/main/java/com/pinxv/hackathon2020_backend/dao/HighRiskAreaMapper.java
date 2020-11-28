package com.pinxv.hackathon2020_backend.dao;

import com.pinxv.hackathon2020_backend.entity.HighRiskArea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author njuselhx
 */
public interface HighRiskAreaMapper extends CrudRepository<HighRiskArea, Integer> {

    /**
     * find area by risk level
     *
     * @param riskLevel risk level
     * @return area list
     */
    List<HighRiskArea> findByRiskLevel(Integer riskLevel);

    /**
     * find area by area name
     * @param areaName area name
     * @return area list, should has 0 or 1 size
     */
    List<HighRiskArea> findByArea(String areaName);

    List<HighRiskArea> findAll();

}
