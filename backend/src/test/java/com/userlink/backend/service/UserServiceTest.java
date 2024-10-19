package com.userlink.backend.service;
import java.util.Date;

import com.userlink.backend.pojo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}