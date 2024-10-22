package com.userlink.backend.controller;/*
 * @author  0kr
 * @version 1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.userlink.backend.pojo.domain.User;
import com.userlink.backend.pojo.domain.request.UserLoginRequest;
import com.userlink.backend.pojo.domain.request.UserRegisterRequest;
import com.userlink.backend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.userlink.backend.constant.UserConstant.ADMIN_ROLE;
import static com.userlink.backend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        //controller层倾向于对请求参数本身的校验，不涉及业务逻辑
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }


    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        //controller层倾向于对请求参数本身的校验，不涉及业务逻辑
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    //获取当前用户登录信息
    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            return null;
        }
        //为了保证频繁更新的数据是正确的，此处需要在service层跑一圈来查数据库更新数据
        long userId = currentUser.getId();
        //todo 校验用户是否合法
        User user = userService.getById(userId);
        return userService.getSafetyUser(user);
    }

    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request) {
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
       List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user ->{
            user.setUserPassword(null);
            return user;
        }).collect(Collectors.toList());
    }

    @PostMapping("/logout")
    public Integer userLogout(HttpServletRequest request) {
        if(request == null){
            return null;
        }
        return userService.userLogout(request);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if(!isAdmin(request)){
            return false;
        }
        if(id <= 0){
            return false;
        }
        //mybatisplus 删除时会自动转换为逻辑删除
        return userService.removeById(id);
    }

    /**
     * 鉴别是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        //鉴权：仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        //防止空指针异常
        if(user == null || user.getUserRole() != ADMIN_ROLE){
            return false;
        }
        return true;
    }
}
