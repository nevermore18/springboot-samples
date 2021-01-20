package pers.xzj.idempotent.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pers.xzj.idempotent.exception.IdempotentException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Description token
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-20 9:44
 * @Version 1.0.0
 */
@Component
public class TokenHelper {

    @Autowired
    private RedisHelper redisHelper;

    public String createToken() {
        String token = UUID.randomUUID().toString();
        redisHelper.setEx(token,token, 1800L);
        return token;
    }

    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token))
                throw new IdempotentException("token不存在");
        }
        // 这里肯定拿到token了
        if (!redisHelper.remove(token))
            throw new IdempotentException("重复的操作");
        return true;
    }
}
