package com.example.mongodb;

import com.example.mongodb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * <p>
 * MongoTemplateTest
 * </p>
 *
 * @author 和耳朵
 * @since 2020-10-14
 */
@SpringBootTest
public class MongoQueryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 单查
     */
    @Test
    public void find() {
        User user = mongoTemplate.findById("d3d24091-30fe-4272-ab70-88fa475a62dd", User.class);
        System.out.println(user);
    }

    /**
     * 按条件查询数据
     */
    @Test
    public void findBy1() {
        // 查询的范围
        Query query = Query.query(Criteria.where("activeStatus").is(1));

        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    /**
     * 按条件查询数据
     */
    @Test
    public void findBy2() {
        // 查询的范围 (password=256156) or (activeStatus = 1 and username = 和耳朵)
        Query query = Query
                .query(new Criteria().orOperator(Criteria.where("password").is("256156"), Criteria.where("activeStatus").is(1).and("username").is("和耳朵")));

        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    /**
     * 批量按类型查询数据
     */
    @Test
    public void findAll() {
        List<User> users = mongoTemplate.findAll(User.class);
        System.out.println(users);
    }

    /**
     * 查询数据Limit
     */
    @Test
    public void findLimit() {
        // 查询结果只要取前两条
        Query query = Query.query(Criteria.where("activeStatus").is(1)).limit(2);
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    /**
     * 查询数据Skip
     */
    @Test
    public void findSkip() {
        // 查询结果跳过前两条
        Query query = Query.query(Criteria.where("activeStatus").is(1)).skip(2);
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    /**
     * 查询数据排序
     */
    @Test
    public void findSort() {
        // 查询结果进行排序
        Query query = Query.query(Criteria.where("activeStatus").is(1)).with(Sort.by("createTime").descending());
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    /**
     * 聚合函数
     */
    @Test
    public void aggregate() {
        // 查询条件
        Query query = Query.query(Criteria.where("activeStatus").is(1)).with(Sort.by("createTime").descending());
        // 支持 count sum avg min max first last...
//        long count = mongoTemplate.count(query, User.class);
//        System.out.println(count);
    }
}
