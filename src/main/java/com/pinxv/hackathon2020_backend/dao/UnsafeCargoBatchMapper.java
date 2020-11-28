package com.pinxv.hackathon2020_backend.dao;

import com.pinxv.hackathon2020_backend.entity.UnsafeCargoBatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/29
 */
public interface UnsafeCargoBatchMapper extends CrudRepository<UnsafeCargoBatch, Integer> {
    List<UnsafeCargoBatch> findAllByBatchNum(String batchNum);
}
