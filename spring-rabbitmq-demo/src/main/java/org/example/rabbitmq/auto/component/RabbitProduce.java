package org.example.rabbitmq.auto.component;

import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.auto.entity.User;
import org.example.rabbitmq.prototype.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
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

    public void send() {
        String message = "Hello 我是作者和耳朵，欢迎关注我。" + LocalDateTime.now().toString();

        System.out.println("Message content : " + message);

        // 指定消息类型
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build();

        rabbitTemplate.send(Producer.QUEUE_NAME,new Message(message.getBytes(StandardCharsets.UTF_8),props));
        System.out.println("消息发送完毕。");
    }

    public void convertAndSend() {
        User user = new User();

        System.out.println("Message content : " + user);

        rabbitTemplate.convertAndSend(Producer.QUEUE_NAME,user);
        System.out.println("消息发送完毕。");
    }

}
