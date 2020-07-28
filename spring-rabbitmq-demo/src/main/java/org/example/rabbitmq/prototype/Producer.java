package org.example.rabbitmq.prototype;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static final String QUEUE_NAME = "erduo";

    public void producer() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 连接到本地server
        connectionFactory.setHost("127.0.0.1");
        // 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 通过连接创建通道
        Channel channel = connection.createChannel();
        // 创建一个名为耳朵的队列，该队列非持久(服务器重启后依然存在)、非独占(非仅用于此链接)、非自动删除(服务器将不再使用的队列删除)
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "hello, 我是耳朵。" + LocalDateTime.now().toString();
        // 发布消息
        // 四个参数为：指定路由器，指定key，指定参数，和二进制数据内容
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
        System.out.println("生产者发送消息结束，发送内容为：" + msg);
        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Producer producer = new Producer();
        producer.producer();
    }
}
