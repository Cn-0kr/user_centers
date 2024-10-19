package com.userlink.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userlink.backend.pojo.domain.User;
import com.userlink.backend.service.UserService;
import com.userlink.backend.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author zhuho
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-10-19 15:39:07
*/

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Mapper
    private UserMapper userMapper;
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        //账户不能重复 + 性能优化
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = this.count(queryWrapper);
        if(count > 0){
            return -1;
        }


        //1.校验 利用common lang依赖中的utils方法进行简化
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        if(userAccount.length()<4){
            return -1;
        }
        if(userPassword.length()< 8 || checkPassword.length()<8){
            return -1;
        }

        //校验特殊字符
        String validPattern = "^a-zA-Z0-9";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return -1;
        }

        //校验密码和校验密码是否相同
        if (!checkPassword.equals(userPassword)) {
            return -1;
        }


        //加盐,插入数据
        final String SALT = "yzl1234";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);

        boolean saveResult =  this.save(user);
        if(!saveResult){
            return -1;
        }
        return user.getId();
    }
}




