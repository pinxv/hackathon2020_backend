package com.pinxv.hackathon2020_backend.controller;

import com.pinxv.hackathon2020_backend.service.AdminUserService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import com.pinxv.hackathon2020_backend.vo.cargo.CargoBatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>description:用户管理接口 </p>
 *
 * @author fengguohao
 * @date 2020/11/28
 */
@RestController
@RequestMapping("/api/AdminUser")
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody LoginUserVO loginUserVO) {
        return this.adminUserService.login(loginUserVO);
    }

    @PostMapping("importCargoBatch")
    public ResponseVO importCargoBatch(@RequestBody CargoBatchVO cargoBatchVO) {
        return this.adminUserService.importCargoBatch(cargoBatchVO);
    }

}
