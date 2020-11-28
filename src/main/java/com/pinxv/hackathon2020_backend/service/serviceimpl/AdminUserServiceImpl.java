package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.AdminUserMapper;
import com.pinxv.hackathon2020_backend.dao.CargoBatchMapper;
import com.pinxv.hackathon2020_backend.entity.AdminUser;
import com.pinxv.hackathon2020_backend.entity.CargoBatch;
import com.pinxv.hackathon2020_backend.entity.ChangeCargoInfo;
import com.pinxv.hackathon2020_backend.service.AdminUserService;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.UserVO;
import com.pinxv.hackathon2020_backend.vo.cargo.CargoBatchVO;
import com.pinxv.hackathon2020_backend.vo.cargo.CargoVO;
import com.pinxv.hackathon2020_backend.vo.cargo.ChangeCargoInfoVO;
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
    private final AdminUserMapper adminUserMapper;

    @Autowired
    private final CargoBatchMapper cargoBatchMapper;

    public AdminUserServiceImpl(AdminUserMapper adminUserMapper, CargoBatchMapper cargoBatchMapper) {
        this.adminUserMapper = adminUserMapper;
        this.cargoBatchMapper = cargoBatchMapper;
    }

    @Override
    public ResponseVO login(LoginUserVO loginUserVO) {
        List<AdminUser> adminUserList = adminUserMapper
                .findByUsernameAndPassword(loginUserVO.getUsername(), loginUserVO.getPassword());
        if (adminUserList.size() != 1) {
            return ResponseVO.buildFailure(LOGIN_FAILURE);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(adminUserList.get(0), userVO);
        return ResponseVO.buildSuccess(userVO);
    }

    @Override
    public ResponseVO importCargoBatch(ChangeCargoInfoVO changeCargoInfoVO) {
        // TODO return a base64 code and save
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO changeCargoBatchInfo(ChangeCargoInfoVO changeCargoInfoVO) {
        return null;
    }

    @Override
    public ResponseVO distributeCargo(ChangeCargoInfoVO changeCargoInfoVO) {
        return null;
    }

}
