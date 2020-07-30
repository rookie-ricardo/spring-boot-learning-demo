package org.example.rabbitmq.auto.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class User implements Serializable {
    private String id = UUID.randomUUID().toString();
    private String name = "和耳朵";
    private Integer age = 22;
    private LocalDateTime crateTime = LocalDateTime.now();
}
