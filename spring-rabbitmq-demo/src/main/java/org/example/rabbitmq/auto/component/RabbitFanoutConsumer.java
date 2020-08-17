package org.example.rabbitmq.auto.component;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.auto.entity.Client;
import org.example.rabbitmq.prototype.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 和耳朵
 * @since 2020-08-13 21:01
 */
@Slf4j
@Component("rabbitFanoutConsumer")
public class RabbitFanoutConsumer {
    @RabbitListener(queues = "fanout1")
    public void onMessage1(Message message, Channel channel) throws Exception {
        log.info("Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

    @RabbitListener(queues = "fanout2")
    public void onMessage2(Message message, Channel channel) throws Exception {
        log.info("Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

}
