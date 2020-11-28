package com.pinxv.hackathon2020_backend.dao;

import com.pinxv.hackathon2020_backend.entity.AdminUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>description: AdminUser数据接口</p>
 *
 * @date 2020/11/28
 */

public interface AdminUserMapper extends CrudRepository<AdminUser, Integer> {
    public List<AdminUser> findAllByUsername(String username);
}
