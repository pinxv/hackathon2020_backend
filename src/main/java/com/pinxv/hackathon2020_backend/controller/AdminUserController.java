package com.pinxv.hackathon2020_backend.controller;

import com.pinxv.hackathon2020_backend.service.AdminUserService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.UUIDPicVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import com.pinxv.hackathon2020_backend.vo.cargo.ChangeCargoInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>description:用户管理接口 </p>
 *
 * @author fengguohao
 * @date 2020/11/28
 */
@RestController
@RequestMapping("/api/adminUser")
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody LoginUserVO loginUserVO) {
        return this.adminUserService.login(loginUserVO);
    }

    @PostMapping("/importCargoBatch")
    public ResponseVO importCargoBatch(@RequestBody ChangeCargoInfoVO changeCargoInfoVO) {
        return this.adminUserService.importCargoBatch(changeCargoInfoVO);
    }

    @PostMapping("/getDetails")
    public ResponseVO getDetails(@RequestBody UUIDPicVO uuidPicVO) {
        return this.adminUserService.getDetails(uuidPicVO);
    }

    @PostMapping("/changeCargoBatchInfoConfirm")
    public ResponseVO changeCargoBatchInfoConfirm(@RequestBody ChangeCargoInfoVO changeCargoInfoVO) {
        return this.adminUserService.changeCargoBatchInfoConfirm(changeCargoInfoVO);
    }

    @PostMapping("/getUUID")
    public ResponseVO getUUID(@RequestBody UUIDPicVO uuidPicVO) {
        return this.adminUserService.getUUID(uuidPicVO);
    }

    /**
     * 已上传货物数量、安全数量、危险数量
     */
    @GetMapping("/countInfo")
    public ResponseVO getCountInfo() {
        return adminUserService.getCountInfo();
    }

}
