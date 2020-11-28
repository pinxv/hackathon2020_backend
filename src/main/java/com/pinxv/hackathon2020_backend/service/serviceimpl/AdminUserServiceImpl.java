package com.pinxv.hackathon2020_backend.service.serviceimpl;

import com.pinxv.hackathon2020_backend.dao.*;
import com.pinxv.hackathon2020_backend.entity.*;
import com.pinxv.hackathon2020_backend.service.AdminUserService;
import com.pinxv.hackathon2020_backend.task.UnsafePlaceUpdateTask;
import com.pinxv.hackathon2020_backend.util.QRCodeUtil;
import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.UUIDPicVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.CountInfoVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.UserVO;
import com.pinxv.hackathon2020_backend.vo.cargo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private final CargoMapper cargoMapper;

    @Autowired
    private final UnsafeCargoBatchMapper unsafeCargoBatchMapper;

    @Autowired
    HighRiskAreaMapper highRiskAreaMapper;

    @Autowired
    UnsafePlaceUpdateTask unsafePlaceUpdateTask;

    public AdminUserServiceImpl(AdminUserMapper adminUserMapper, CargoBatchMapper cargoBatchMapper, ChangeCargoInfoMapper changeCargoInfoMapper, CargoMapper cargoMapper, UnsafeCargoBatchMapper unsafeCargoBatchMapper) {
        this.adminUserMapper = adminUserMapper;
        this.cargoBatchMapper = cargoBatchMapper;
        this.changeCargoInfoMapper = changeCargoInfoMapper;
        this.cargoMapper = cargoMapper;
        this.unsafeCargoBatchMapper = unsafeCargoBatchMapper;
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        changeCargoInfoVO.setTimestamp(ts);
        ChangeCargoInfo changeCargoInfo = new ChangeCargoInfo();
        BeanUtils.copyProperties(changeCargoInfoVO, changeCargoInfo);
        this.changeCargoInfoMapper.save(changeCargoInfo);
        unsafePlaceUpdateTask.updateUnsafePlace(changeCargoInfo);
        CargoBatch cargoBatch = new CargoBatch();
        BeanUtils.copyProperties(changeCargoInfoVO, cargoBatch);
        this.cargoBatchMapper.save(cargoBatch);
        String base64 = QRCodeUtil.encodeQRCode(batchNumber);
        for (int i = 0; i < changeCargoInfo.getSum(); i++) {
            Cargo cargo = new Cargo();
            BeanUtils.copyProperties(changeCargoInfoVO, cargo);
            String suffix = Integer.toHexString(i);
            int len = suffix.length();
            for (int j = 0; j < 16 - len; j++) {
                suffix = "0" + suffix;
            }
            cargo.setSerialNumber(batchNumber + suffix);
            this.cargoMapper.save(cargo);
        }
        return ResponseVO.buildSuccess(base64);
    }

    @Override
    public ResponseVO changeCargoBatchInfoConfirm(ChangeCargoInfoVO changeCargoInfoVO) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        changeCargoInfoVO.setTimestamp(ts);
        ChangeCargoInfo changeCargoInfo = new ChangeCargoInfo();
        BeanUtils.copyProperties(changeCargoInfoVO, changeCargoInfo);
        this.changeCargoInfoMapper.save(changeCargoInfo);
        unsafePlaceUpdateTask.updateUnsafePlace(changeCargoInfo);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getDetails(UUIDPicVO uuidPicVO) {
        String UUID = QRCodeUtil.decodeQRCode(uuidPicVO.getBase64());
        if (UUID == null) {
            return ResponseVO.buildFailure("查询失败，请检查UUID是否存在");
        }
        List<ChangeCargoInfo> changeCargoInfoList = changeCargoInfoMapper.findAllByBatchNumber(UUID);
        if (changeCargoInfoList.isEmpty()) {
            return ResponseVO.buildFailure("查询失败，没有匹配的UUID，请确认二维码清晰有效");
        } else {
            Collections.sort(changeCargoInfoList);
            List<ChangeCargoPlaceVO> changeCargoPlaceVOList = new ArrayList<>();
            for (ChangeCargoInfo changeCargoInfo : changeCargoInfoList) {
                changeCargoPlaceVOList.add(new ChangeCargoPlaceVO(changeCargoInfo));
            }
            String description = changeCargoInfoList.get(0).getDescription();
            List<UnsafeInfoVO> unsafeInfoVOList = new ArrayList<>();
            List<UnsafeCargoBatch> unsafeCargoBatchList = unsafeCargoBatchMapper.findAllByBatchNum(UUID);
            for(UnsafeCargoBatch unsafeCargoBatch:unsafeCargoBatchList){
                UnsafeInfoVO unsafeInfoVO = new UnsafeInfoVO();
                unsafeInfoVO.setActualLoc(unsafeCargoBatch.getActualPlace());
                unsafeInfoVO.setUnsafeLoc(unsafeCargoBatch.getHighRiskPlace());
                unsafeInfoVOList.add(unsafeInfoVO);
            }
            CargoChangeDetailsVO cargoChangeDetailsVO = new CargoChangeDetailsVO(UUID, description, changeCargoPlaceVOList,unsafeInfoVOList);
            return ResponseVO.buildSuccess(cargoChangeDetailsVO);
        }
    }

    @Override
    public ResponseVO getUUID(UUIDPicVO uuidPicVO) {
        String UUID = QRCodeUtil.decodeQRCode(uuidPicVO.getBase64());
        if (UUID == null) {
            return ResponseVO.buildFailure("查询失败，请检查UUID是否存在");
        }
        List<CargoBatch> cargoBatchList = cargoBatchMapper.findByBatchNumber(UUID);
        if (cargoBatchList.isEmpty()) {
            return ResponseVO.buildFailure("查询失败，请检查UUID是否存在");
        } else {
            CargoBatch cargoBatch = cargoBatchList.get(0);
            CargoBatchVO cargoBatchVO = new CargoBatchVO();
            BeanUtils.copyProperties(cargoBatch, cargoBatchVO);
            return ResponseVO.buildSuccess(cargoBatchVO);
        }
    }

    @Override
    public ResponseVO getCountInfo() {
        Integer batchNum = (int) cargoBatchMapper.count();
        Integer unsafeNum = cargoBatchMapper.countByIsSafeEquals(false);
        Integer safeNum = batchNum - unsafeNum;
        CountInfoVO countInfoVO = new CountInfoVO();
        countInfoVO.setBatchNum(batchNum);
        countInfoVO.setUnsafeNum(unsafeNum);
        countInfoVO.setSafeNum(safeNum);
        return ResponseVO.buildSuccess(countInfoVO);
    }

    @Override
    public ResponseVO getHistory(String username) {
        List<CargoBatch> cargoBatchList = cargoBatchMapper.findByCreator(username);
        List<CargoBatchVO> cargoBatchVOList = new ArrayList<>();
        for (CargoBatch cargoBatch : cargoBatchList) {
            CargoBatchVO cargoBatchVO = new CargoBatchVO();
            BeanUtils.copyProperties(cargoBatch, cargoBatchVO);
            cargoBatchVOList.add(cargoBatchVO);
        }
        return ResponseVO.buildSuccess(cargoBatchVOList);
    }
}
