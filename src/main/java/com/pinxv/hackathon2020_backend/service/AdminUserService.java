package com.pinxv.hackathon2020_backend.service;

import com.pinxv.hackathon2020_backend.vo.ResponseVO;
import com.pinxv.hackathon2020_backend.vo.adminuser.LoginUserVO;
import com.pinxv.hackathon2020_backend.vo.cargo.CargoBatchVO;
import com.pinxv.hackathon2020_backend.vo.cargo.CargoVO;
import com.pinxv.hackathon2020_backend.vo.cargo.ChangeCargoInfoVO;
import org.springframework.stereotype.Service;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public interface AdminUserService {

    ResponseVO login(LoginUserVO loginUserVO);

    ResponseVO importCargoBatch(ChangeCargoInfoVO changeCargoInfoVO);

    ResponseVO changeCargoBatchInfoConfirm(ChangeCargoInfoVO changeCargoInfoVO);

}
