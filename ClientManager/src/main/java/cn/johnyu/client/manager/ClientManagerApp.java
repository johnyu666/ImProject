package cn.johnyu.client.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication

public class ClientManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientManagerApp.class, args);
    }

    @Autowired
    private RedisConnectionFactory factory; //redis依赖的引入，条件生成该对象

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){

        RedisTemplate redisTemplate= new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //TODO:此部分代码，可以进行进一步的定制
        return redisTemplate;
    }

}