package pers.xzj.idempotent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description go
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-19 14:30
 * @Version 1.0.0
 */
@SpringBootApplication
public class Go {

    public static void main(String[] args) {
        SpringApplication.run(Go.class);
    }

}
