package org.example.rabbitmq.auto.component;

import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.auto.entity.Client;
import org.example.rabbitmq.auto.entity.User;
import org.example.rabbitmq.prototype.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.NoBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public void sendObject() {
        Client client = new Client();

        System.out.println("Message content : " + client);

        rabbitTemplate.convertAndSend(RabbitJsonConsumer.JSON_QUEUE,client);
        System.out.println("消息发送完毕。");
    }

    public void sendFanout() {
        Client client = new Client();

        // 应读者要求，以后代码打印的地方都会改成log方式，这是一种良好的编程习惯，用System.out.println一般是不推荐的。
        log.info("Message content : " + client);

        rabbitTemplate.convertAndSend("fanoutExchange",null,client);
        System.out.println("消息发送完毕。");
    }

    public void sendDirect() {
        Client client = new Client();

        log.info("Message content : " + client);

        rabbitTemplate.convertAndSend("directExchange","sms",client);
        System.out.println("消息发送完毕。");
    }

    public void sendTopic() {
        Client client = new Client();

        log.info("Message content : " + client);

        rabbitTemplate.convertAndSend("topicExchange","sms.liantong",client);
        System.out.println("消息发送完毕。");
    }

    public void sendAndConfirm() {
        User user = new User();

        log.info("Message content : " + user);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(Producer.QUEUE_NAME,user,correlationData);
        log.info("消息发送完毕。");

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("CorrelationData content : " + correlationData);
                log.info("Ack status : " + ack);
                log.info("Cause content : " + cause);
                if(ack){
                    log.info("消息成功发送，订单入库，更改订单状态");
                }else{
                    log.info("消息发送失败："+correlationData+", 出现异常："+cause);
                }
            }
        });
    }

    public void sendAndReturn() {
        User user = new User();

        log.info("Message content : " + user);

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("被退回的消息为：{}", message);
            log.info("replyCode：{}", replyCode);
            log.info("replyText：{}", replyText);
            log.info("exchange：{}", exchange);
            log.info("routingKey：{}", routingKey);
        });

        rabbitTemplate.convertAndSend("fail",user);
        log.info("消息发送完毕。");
    }

}
