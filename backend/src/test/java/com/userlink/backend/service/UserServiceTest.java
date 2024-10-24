package com.userlink.backend.service;

import com.userlink.backend.pojo.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

/*
 * @author  0kr
 * @version 1.0
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    /*
    利用generateallsetters和sonarlint 进行测试代码的快速编译
    方法：在user上点击黄色灯泡即可
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("linker");
        user.setAvatarUrl("https://th.bing.com/th?id=ORMS.e4047cfea0c2aaa18b947e579e8e270a&pid=Wdp&w=300&h=156&qlt=90&c=1&rs=1&dpr=1.5&p=0");
        user.setUserAccount("123");
        user.setGender(0);
        user.setUserPassword("qwerty");
        user.setPhone("123456");
        user.setEmail("qq@gmail.com");
        boolean result = userService.save(user);
        System.out.println(user.getId());
    }

    @Test
    void userRegister() {
        String userAccount = "123";
        String password = "qw";
        String checkPassword = "qw123";
        long result = userService.userRegister(userAccount, password, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "hushangzhanshen";
        password = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, password, checkPassword);
        System.out.println(result);
    }

    @Test
    void userLogin() {
        System.out.println( DigestUtils.md5DigestAsHex(("yzl1234" + "12345678").getBytes()) );
    }
}