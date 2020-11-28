package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.AdminUserMapper;
import com.pinxv.hackathon2020_backend.entity.AdminUser;
import com.pinxv.hackathon2020_backend.enums.StatusCode;
import com.pinxv.hackathon2020_backend.service.AdminUserService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private static final String LOGIN_FAILURE = "登录失败";

    @Autowired
    private AdminUserMapper adminUserMapper;

    public AdminUserServiceImpl(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    @Override
    public ResponseVO login(LoginUserVO loginUserVO) {
        List<AdminUser> adminUserList = adminUserMapper.findAllByUsername(loginUserVO.getUsername());
        if (adminUserList.size() != 1) {
            return ResponseVO.buildFailure(LOGIN_FAILURE);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(adminUserList.get(0), userVO);
        return ResponseVO.buildSuccess(userVO);
    }

}
