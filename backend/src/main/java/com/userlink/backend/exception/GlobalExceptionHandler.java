package com.userlink.backend.exception;/*
 * @author  0kr
 * @version 1.0
 */

import com.userlink.backend.common.BaseResponse;
import com.userlink.backend.common.ErrorCode;
import com.userlink.backend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 集中处理日志
 * 统一封装响应给前端的异常
 * 同时不暴露服务器内部状态，屏蔽项目框架本身的异常
 * 实现方法：aop
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException: " + e.getMessage(), e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }

    // 可以添加一个通用的异常处理方法
    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e) {
        log.error("Exception: " + e.getMessage(), e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误", "");
    }

}
