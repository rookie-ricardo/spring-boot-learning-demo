package org.example.rabbitmq.auto.component;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.prototype.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 和耳朵
 * @since 2020-07-29 21:01
 */
@Slf4j
@Component("rabbitConsumer")
public class RabbitConsumer {

    @RabbitListener(queues = Producer.QUEUE_NAME)
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("消息已确认");
    }

}
