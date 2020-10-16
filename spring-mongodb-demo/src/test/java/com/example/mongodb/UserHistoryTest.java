package com.example.mongodb;

import com.example.mongodb.entity.HistoryGoods;
import com.example.mongodb.entity.User;
import com.example.mongodb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
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
public class UserHistoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    /**
     * 批量保存数据 6d053675-5fba-4338-9577-02810c3413d1
     */
    @Test
    public void saveBatch() {
        for (int i = 0; i < 2000; i++) {
            User user = new User();
            System.out.println(user.getId());
            List<HistoryGoods> goodsList = new ArrayList<>(501);
            user.setUsername(user.getUsername() + i);
            for (int j = 0; j < 500; j++) {
                HistoryGoods goods = new HistoryGoods();
                goodsList.add(goods);
            }
            user.setGoodsList(goodsList);
            mongoTemplate.save(user);
        }
    }

    /**
     * 查询历史浏览商品数据
     */
    @Test
    public void getHistoryGoods() {
        List<HistoryGoods> historyGoods = userService.getHistoryGoods("小米", 10, 10);
        System.out.println(historyGoods);
    }


}
