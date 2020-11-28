package com.pinxv.hackathon2020_backend.dao;

import com.pinxv.hackathon2020_backend.entity.CargoBatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author njuselhx
 */
public interface CargoBatchMapper extends CrudRepository<CargoBatch, Integer> {

    /**
     * find cargo batch by batch number
     *
     * @param batchNumber batch number
     * @return cargo batch list, should has 1 size
     */
    List<CargoBatch> findByBatchNumber(String batchNumber);

    /**
     * find cargo batch by creator
     *
     * @param creator creator
     * @return cargo batch list
     */
    List<CargoBatch> findByCreator(String creator);

}
