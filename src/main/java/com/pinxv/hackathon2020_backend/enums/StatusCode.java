package com.pinxv.hackathon2020_backend.enums;

/**
 * @author fengguohao
 */
public enum StatusCode {

    /**
     * success state
     */
    SUCCESS(200,"success"),

    /**
     * failure state
     */
    FAILURE(-1,"failure");

    int code;
    String message;

    StatusCode() {
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
