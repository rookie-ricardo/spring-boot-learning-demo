package com.example.mongodb.service.impl;

import com.example.mongodb.entity.HistoryGoods;
import com.example.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<HistoryGoods> getHistoryGoods(String key, int start, int end) {

        // db.user.find({"goodsList":{"$elemMatch":{"goodsTitle":/fb/}}},{"goodsList.$":1}).limit(2).pretty()
        // 根据SQL写代码

        return null;
    }
}
