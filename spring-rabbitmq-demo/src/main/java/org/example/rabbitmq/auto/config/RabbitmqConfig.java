package org.example.rabbitmq.auto.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author 张笑生
 * @since 2020-07-29 23:00
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue erduo() {
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下持久化即可
        return new Queue("erduo",false);
    }

}
