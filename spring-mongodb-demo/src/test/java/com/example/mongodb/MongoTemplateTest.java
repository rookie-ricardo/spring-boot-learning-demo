package com.example.mongodb;

import com.example.mongodb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * <p>
 * MongoTemplateTest
 * </p>
 *
 * @author 张笑生
 * @since 2020-09-01 22:46
 */
@SpringBootTest
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存数据
     */
    @Test
    public void save(){
        User user = new User();
        User save = mongoTemplate.save(user);
        System.out.println(save);
    }
}
