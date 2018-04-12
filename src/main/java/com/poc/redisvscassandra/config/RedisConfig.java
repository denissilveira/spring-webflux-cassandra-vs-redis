package com.poc.redisvscassandra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.poc.redisvscassandra.model.entity.User;

@Configuration
public class RedisConfig {
    
    @Bean
    public ReactiveRedisTemplate<String, User> reactiveJsonUserRedisTemplate(
        ReactiveRedisConnectionFactory connectionFactory) {

        RedisSerializationContext<String, User> serializationContext = RedisSerializationContext
            .<String, User>newSerializationContext(new StringRedisSerializer())
            .hashKey(new StringRedisSerializer())
            .hashValue(new Jackson2JsonRedisSerializer<>(User.class))
            .build();

        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

}
