package org.example.rabbitmq.auto.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitProduceTest {
    @Autowired
    private RabbitProduce rabbitProduce;

    @Test
    public void sendSimpleMessage() {
        rabbitProduce.send();
        rabbitProduce.convertAndSend();
    }
}