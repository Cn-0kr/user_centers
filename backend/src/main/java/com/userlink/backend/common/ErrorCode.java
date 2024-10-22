package com.userlink.backend.common;/*
 * @author  0kr
 * @version 1.0
 */

/**
 * 错误码
 * @author 0kr
 */

public enum ErrorCode {
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    SYSTEM_ERROR(50000,"系统内部异常",""),
    NO_AUTH(40101,"没有请求权限","");

    private final int errorCode;
    private final String errorMsg;
    private final String description;

    ErrorCode(int errorCode, String errorMsg, String description) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getDescription() {
        return description;
    }
}
