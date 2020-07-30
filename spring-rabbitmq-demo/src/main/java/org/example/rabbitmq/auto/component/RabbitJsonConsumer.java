package org.example.rabbitmq.auto.component;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.auto.entity.Client;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
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
@Component("rabbitJsonConsumer")
@RabbitListener(queues = RabbitJsonConsumer.JSON_QUEUE)
public class RabbitJsonConsumer {
    public static final String JSON_QUEUE = "erduo_json";

    @RabbitHandler
    public void onMessage(Client client, Channel channel) throws Exception {
        System.out.println("Message content : " + client);
//        channel.basicAck(messageProperties.getDeliveryTag(),false);
        System.out.println("消息已确认");
    }

}
