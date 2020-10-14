package com.example.mongodb;

import com.example.mongodb.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Collection;

/**
 * <p>
 * MongoTemplateTest
 * </p>
 *
 * @author 和耳朵
 * @since 2020-10-14
 */
@SpringBootTest
public class MongoMergeTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存数据
     */
    @Test
    public void save() {
        User user = new User();
        User save = mongoTemplate.insert(user);
        System.out.println(save);
    }

    /**
     * 批量保存数据
     */
    @Test
    public void saveBatch() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        Collection<User> users = mongoTemplate.insertAll(Arrays.asList(user1, user2, user3));
        System.out.println(users);
    }

    /**
     * 更新数据1
     */
    @Test
    public void update1() {
        // 指定ID后，可以使用Save方法保存数据
        User user = new User();
        user.setId("0f4551e0-d8b4-4bce-b166-bf04e7a50f4f");
        user.setActiveStatus(0);
        mongoTemplate.save(user);
        System.out.println(user);
    }

    /**
     * 更新数据2
     */
    @Test
    public void update2() {
        // 指定要更新的范围
        Query query = Query.query(Criteria.where("id").is("851fb341-a3bd-4560-bc48-03d5054b4ec1"));

        // 指定要被更新的值 set方法如果key不存在则创建一个新的key
        Update update = Update.update("activeStatus", "0").set("test", "hahaha");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        System.out.println(updateResult);
    }

    /**
     * 单个删除数据
     */
    @Test
    public void delete1() {
        User user = new User();
        user.setId("0f4551e0-d8b4-4bce-b166-bf04e7a50f4f");

        DeleteResult deleteResult = mongoTemplate.remove(user);
        System.out.println(deleteResult);
    }

    /**
     * 范围删除数据
     */
    @Test
    public void delete2() {
        // 指定要删除的范围
        Query query = Query.query(Criteria.where("activeStatus").is("0"));

        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        System.out.println(deleteResult);
    }
}
