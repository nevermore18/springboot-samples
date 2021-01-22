package pers.xzj.idempotent.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @Description redis
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-19 15:05
 * @Version 1.0.0
 */
@Component
public class RedisHelper {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    public boolean setEx(String key, Object value, Long timeout) {
        try {
            ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
            ops.set(key,value);
            redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean remove(String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }
}
