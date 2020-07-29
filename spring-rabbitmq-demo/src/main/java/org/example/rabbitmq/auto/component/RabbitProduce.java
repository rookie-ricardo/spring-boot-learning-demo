package org.example.rabbitmq.auto.component;

import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.prototype.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 和耳朵
 * @since 2020-07-29 21:01
 */
@Slf4j
@Component("rabbitProduce")
public class RabbitProduce {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSimpleMessage() {
        String message = "Hello 我是作者和耳朵，欢迎关注我。" + LocalDateTime.now().toString();
        System.out.println("Message : " + message);
        rabbitTemplate.send(Producer.QUEUE_NAME,new Message(message.getBytes(StandardCharsets.UTF_8), null));
    }

}
