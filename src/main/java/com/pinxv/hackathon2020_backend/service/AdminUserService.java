package com.pinxv.hackathon2020_backend.service;

import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public interface AdminUserService {
    public ResponseVO login(LoginUserVO loginUserVO);
}
