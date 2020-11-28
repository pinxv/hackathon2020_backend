package com.pinxv.hackathon2020_backend.enums;

/**
 * @author njuselhx
 */

public enum RiskLevelCode {

    /**
     * low risk
     */
    LOW(0),

    /**
     * medium risk
     */
    MEDIUM(100),

    /**
     * high risk
     */
    HIGH(666);

    int code;

    public int getCode() {
        return code;
    }

    RiskLevelCode(int code) {
        this.code = code;
    }



}
