package com.example.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户对象
 * </p>
 *
 * @author 和耳朵
 * @since 2020-09-01
 */
@Data
@Document(collection = "user")
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String username = "和耳朵";

    private String password = "123456";

    private Integer activeStatus = 1;

    private LocalDateTime createTime = LocalDateTime.now();

    private List<HistoryGoods> goodsList;
}
