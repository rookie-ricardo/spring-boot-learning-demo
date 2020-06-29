package org.example.security;

import org.example.security.auth.entity.UserInfo;
import org.example.security.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertData(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test");
        userInfo.setPassword(passwordEncoder.encode("123456"));
        userInfo.setActiveStatus(1);
        userInfo.setCreateTime(LocalDateTime.now());

        boolean save = userService.save(userInfo);
        if (save) {
            System.out.println("插入成功");
        }
    }

    @Test
    public void encode(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}
