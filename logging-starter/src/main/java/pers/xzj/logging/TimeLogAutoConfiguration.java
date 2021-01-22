package pers.xzj.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Description auto
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-22 14:19
 * @Version 1.0.0
 */
@Configuration
@Aspect
@EnableAspectJAutoProxy
@ConditionalOnProperty(prefix = "time.log", name = "enable", havingValue = "true", matchIfMissing = true)
public class TimeLogAutoConfiguration {

    private static final Logger logger = getLogger(TimeLogAutoConfiguration.class);

    @Around("@annotation(pers.xzj.logging.TimeLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // public java.lang.String pers.xzj.xxxx.controller.hello()
        String name = proceedingJoinPoint.getSignature().toLongString().split(" ")[2];
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("方法 {} 耗时 {} ms", name, endTime - startTime);
        return result;
    }

}
