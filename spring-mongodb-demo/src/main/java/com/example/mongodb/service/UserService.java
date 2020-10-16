package com.example.mongodb.service;

import com.example.mongodb.entity.HistoryGoods;

import java.util.List;

public interface UserService {

    List<HistoryGoods> getHistoryGoods(String key, int start, int end);

}
