package com.pinxv.hackathon2020_backend.controller;

import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>description: </p>
 *
 * @author fengguohao
 * @date 2020/11/28
 */
@RestController
@RequestMapping("/api/adminuser")
public class AdminUserController {

    @PostMapping("/login")
    public ResponseVO login(@RequestBody LoginUserVO loginUserVO) {
        return null;
    }

}
