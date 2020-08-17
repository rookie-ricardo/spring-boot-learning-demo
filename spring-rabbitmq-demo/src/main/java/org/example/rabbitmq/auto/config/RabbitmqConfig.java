package org.example.rabbitmq.auto.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author 和耳朵
 * @since 2020-07-29 21:01
 */
@Configuration
public class RabbitmqConfig {
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Bean
    public Queue erduo() {
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下持久化即可
        return new Queue("erduo",true);
    }

    @Bean
    public Queue erduoJson() {
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下持久化即可
        return new Queue("erduo_json",true);
    }

    @Bean
    public Queue fanout1() {
        return new Queue("fanout1");
    }

    @Bean
    public Queue fanout2() {
        return new Queue("fanout2");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        // 三个构造参数：name durable autoDelete
        return new FanoutExchange("fanoutExchange", false, false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(fanout1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(fanout2()).to(fanoutExchange());
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter(jacksonObjectMapper);
    }

}
