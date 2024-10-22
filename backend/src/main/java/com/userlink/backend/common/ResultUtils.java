package com.userlink.backend.common;/*
 * @author  0kr
 * @version 1.0
 */

/**
 * 返回工具类
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0,data,"ok");
    }

    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getErrorCode(), message, description);
    }
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getErrorCode(), errorCode.getErrorMsg(),description);
    }

}
