package org.example.rabbitmq.auto.component;

import org.example.rabbitmq.prototype.Producer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单消费者
 * </p>
 *
 * @author 和耳朵
 * @since 2020-07-29 21:01
 */
@Component("rabbitConsumer")
@RabbitListener(queues = Producer.QUEUE_NAME)
public class RabbitConsumer {

    @RabbitHandler
    public void onMessage(byte[] bytes) throws Exception {
        String content = new String(bytes);
        System.out.println("receive msg : " + content);

    }

}
