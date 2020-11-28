package com.pinxv.hackathon2020_backend.vo;

import com.pinxv.hackathon2020_backend.enums.StatusCode;
import lombok.Data;

/**
 * @author njuselhx
 */
@Data
public class ResponseVO {

    public Integer code;

    public String message;

    public Object data;

    private ResponseVO() {
    }

    private ResponseVO(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public static ResponseVO buildSuccess(String message, Object data) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.code = StatusCode.SUCCESS.getCode();
        responseVO.message = message;
        responseVO.data = data;
        return responseVO;
    }

    public static ResponseVO buildSuccess(Object data) {
        return new ResponseVO(StatusCode.SUCCESS, data);
    }

    public static ResponseVO buildSuccess() {
        return new ResponseVO(StatusCode.SUCCESS, null);
    }

    public static ResponseVO buildFailure(String message) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.code = StatusCode.FAILURE.getCode();
        responseVO.message = message;
        return responseVO;
    }

    public static ResponseVO buildFailure(Object data) {
        return new ResponseVO(StatusCode.FAILURE, data);
    }

    public static ResponseVO buildFailure() {
        return new ResponseVO(StatusCode.FAILURE, null);
    }

    public static ResponseVO buildFailure(StatusCode statusCode) {
        return new ResponseVO(statusCode, null);
    }
}
