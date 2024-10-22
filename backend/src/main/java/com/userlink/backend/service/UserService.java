package com.userlink.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.userlink.backend.pojo.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author zhuho
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-10-19 15:39:07
*/
public interface UserService extends IService<User> {

    /**
     * 前端用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * @param userAccount
     * @param userPassword
     * @param request
     * @return 返回脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 脱敏子函数
     * @param user
     * @return 返回脱敏信息
     */
    User getSafetyUser(User user);


    /**
     * 用户注销
     * @param request
     * @return null
     */
    int userLogout(HttpServletRequest request);
}
