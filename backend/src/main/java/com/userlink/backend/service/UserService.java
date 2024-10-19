package com.userlink.backend.service;

import com.userlink.backend.pojo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhuho
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-10-19 15:39:07
*/
public interface UserService extends IService<User> {
    /**
     * 前端用户注释
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
