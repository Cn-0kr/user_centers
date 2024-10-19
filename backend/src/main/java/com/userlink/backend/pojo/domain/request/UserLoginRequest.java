package com.userlink.backend.pojo.domain.request;/*
 * @author  0kr
 * @version 1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 */
@Data
public class UserLoginRequest implements Serializable {
    private String userAccount;
    private String userPassword;
}
