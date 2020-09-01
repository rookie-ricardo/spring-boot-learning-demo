package com.example.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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

    private String username;

    private String password;

    private Integer activeStatus;

    private LocalDateTime createTime = LocalDateTime.now();


}
