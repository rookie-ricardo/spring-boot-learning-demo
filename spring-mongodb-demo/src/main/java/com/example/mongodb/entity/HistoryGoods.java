package com.example.mongodb.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class HistoryGoods {
    private String goodsId = UUID.randomUUID().toString();
    private String goodsTitle = "小米智能手机" + this.goodsId;
    private Integer goodsPrice = 249900;
    private String goodsPicture = "/data/goods/picture";
}
