package com.userlink.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userlink.backend.pojo.domain.User;
import com.userlink.backend.service.UserService;
import com.userlink.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhuho
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-10-19 15:39:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




