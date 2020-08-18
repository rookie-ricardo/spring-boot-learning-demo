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

    @Test
    public void sendJSONMessage() {
        rabbitProduce.sendObject();
    }

    @Test
    public void sendFanoutMessage() {
        rabbitProduce.sendFanout();
    }

    @Test
    public void sendDirectMessage() {
        rabbitProduce.sendDirect();
    }

    @Test
    public void sendTopicMessage() {
        rabbitProduce.sendTopic();
    }

    @Test
    public void sendAndConfirm() throws InterruptedException {
        rabbitProduce.sendAndConfirm();
        Thread.sleep(3000);
    }

    @Test
    public void sendAndReturn() throws InterruptedException {
        rabbitProduce.sendAndReturn();
        Thread.sleep(3000);
    }
}