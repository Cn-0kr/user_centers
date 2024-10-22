package com.userlink.backend.controller;/*
 * @author  0kr
 * @version 1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.userlink.backend.common.BaseResponse;
import com.userlink.backend.common.ErrorCode;
import com.userlink.backend.common.ResultUtils;
import com.userlink.backend.exception.BusinessException;
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
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null){
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        //controller层倾向于对请求参数本身的校验，不涉及业务逻辑
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        long result =  userService.userRegister(userAccount, userPassword, checkPassword);

        return ResultUtils.success(result);
    }


    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        //controller层倾向于对请求参数本身的校验，不涉及业务逻辑
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        User user =  userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    //获取当前用户登录信息
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            return null;
        }
        //为了保证频繁更新的数据是正确的，此处需要在service层跑一圈来查数据库更新数据
        long userId = currentUser.getId();
        //todo 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser =  userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(String username, HttpServletRequest request) {
        if(!isAdmin(request)){
//            return ResultUtils.success(new ArrayList<>());
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
       List<User> userList = userService.list(queryWrapper);
        List<User> list =  userList.stream().map(user ->{
            user.setUserPassword(null);
            return user;
        }).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if(request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if(id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //mybatisplus 删除时会自动转换为逻辑删除
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
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
