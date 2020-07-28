package org.example.rabbitmq.prototype;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public void consumer() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 连接到本地server
        connectionFactory.setHost("127.0.0.1");
        // 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 通过连接创建通道
        Channel channel = connection.createChannel();
        // 创建一个名为耳朵的队列，该队列非持久(服务器重启后依然存在)、非独占(非仅用于此链接)、非自动删除(服务器将不再使用的队列删除)
        channel.queueDeclare(Producer.QUEUE_NAME, false, false, false, null);
        // 创建消费者，阻塞接收消息
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("-------------------------------------------");
                System.out.println("consumerTag : " + consumerTag);
                System.out.println("exchange : " + envelope.getExchange());
                System.out.println("routing key : " + envelope.getRoutingKey());
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("消息内容" + msg);
                // 消息确认
                channel.basicAck(envelope.getDeliveryTag(), false);
                System.out.println("消息已确认");
            }
        };
        channel.basicConsume(Producer.QUEUE_NAME, consumer);
//        channel.close();
//        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Consumer consumer = new Consumer();
        consumer.consumer();
    }
}
