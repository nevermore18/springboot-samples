package pers.xzj.idempotene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import pers.xzj.idempotent.Go;

import java.util.HashMap;

/**
 * @Description test
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-19 15:00
 * @Version 1.0.0
 */
@SpringBootTest(classes = Go.class)
public class Test {

    @Autowired
    RedisTemplate redisTemplate;

    @org.junit.jupiter.api.Test
    void test() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("xzj", 123);
        map.put(444, true);
        redisTemplate.opsForValue().set("xzj", map);
        redisTemplate.opsForHash().put("hash", "hash", map);
        redisTemplate.opsForList().leftPush("list", map);
        redisTemplate.opsForList().leftPush("list", map);
        redisTemplate.opsForSet().add("set", map);
    }
}
