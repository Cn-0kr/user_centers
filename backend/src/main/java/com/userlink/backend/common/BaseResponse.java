package com.userlink.backend.common;/*
 * @author  0kr
 * @version 1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {
   private int code;
   private String msg;
   private T data;
   private String description;

   public BaseResponse(int code, T data, String msg) {
      this(code,data,msg,"");
   }
   public BaseResponse(int code, T data, String msg, String description) {
      this.code = code;
      this.data = data;
      this.msg = msg;
      this.description = description;
   }

   public BaseResponse(ErrorCode errorCode) {
      this(errorCode.getErrorCode(), null, errorCode.getErrorMsg(), errorCode.getDescription());
   }
}
