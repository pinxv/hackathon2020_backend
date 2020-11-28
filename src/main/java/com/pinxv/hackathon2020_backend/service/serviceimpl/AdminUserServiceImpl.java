package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.AdminUserMapper;
import com.pinxv.hackathon2020_backend.dao.CargoBatchMapper;
import com.pinxv.hackathon2020_backend.dao.ChangeCargoInfoMapper;
import com.pinxv.hackathon2020_backend.entity.AdminUser;
import com.pinxv.hackathon2020_backend.entity.Cargo;
import com.pinxv.hackathon2020_backend.entity.CargoBatch;
import com.pinxv.hackathon2020_backend.entity.ChangeCargoInfo;
import com.pinxv.hackathon2020_backend.service.AdminUserService;
import com.pinxv.hackathon2020_backend.util.QRCodeUtil;
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
import java.util.UUID;

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

    @Autowired
    private final ChangeCargoInfoMapper changeCargoInfoMapper;

    public AdminUserServiceImpl(AdminUserMapper adminUserMapper, CargoBatchMapper cargoBatchMapper, ChangeCargoInfoMapper changeCargoInfoMapper) {
        this.adminUserMapper = adminUserMapper;
        this.cargoBatchMapper = cargoBatchMapper;
        this.changeCargoInfoMapper = changeCargoInfoMapper;
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
        String batchNumber = String.valueOf(UUID.randomUUID());
        changeCargoInfoVO.setBatchNumber(batchNumber);
        ChangeCargoInfo changeCargoInfo = new ChangeCargoInfo();
        BeanUtils.copyProperties(changeCargoInfoVO, changeCargoInfo);
        this.changeCargoInfoMapper.save(changeCargoInfo);
        CargoBatch cargoBatch = new CargoBatch();
        BeanUtils.copyProperties(changeCargoInfoVO, cargoBatch);
        this.cargoBatchMapper.save(cargoBatch);
        String base64 = QRCodeUtil.encodeQRCode(batchNumber);
        for (int i = 0; i < changeCargoInfo.getSum(); i++) {
            Cargo cargo = new Cargo();
            BeanUtils.copyProperties(changeCargoInfoVO, cargo);
            String suffix = Integer.toHexString(i);
            int len = suffix.length();
            for (int j = 0; j < 16 - len; i++) {
                suffix = "0" + suffix;
            }
            cargo.setSerialNumber(batchNumber + suffix);
        }
        return ResponseVO.buildSuccess(base64);
    }

    @Override
    public ResponseVO changeCargoBatchInfo(ChangeCargoInfoVO changeCargoInfoVO) {
        ChangeCargoInfo changeCargoInfo = new ChangeCargoInfo();
        BeanUtils.copyProperties(changeCargoInfoVO, changeCargoInfo);
        this.changeCargoInfoMapper.save(changeCargoInfo);
        return ResponseVO.buildSuccess();
    }

}
