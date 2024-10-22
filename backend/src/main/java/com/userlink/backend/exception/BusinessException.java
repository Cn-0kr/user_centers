package com.userlink.backend.exception;/*
 * @author  0kr
 * @version 1.0
 */

import com.userlink.backend.common.ErrorCode;

/**
 * 业务异常处理器
 */
public class BusinessException extends RuntimeException {

    private final int code;
    private final String description;
    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getErrorMsg());
        this.code = errorCode.getErrorCode();
        this.description = description;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.code = errorCode.getErrorCode();
        this.description = errorCode.getDescription();
    }

}
