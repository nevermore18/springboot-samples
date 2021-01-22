package pers.xzj.idempotent.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.xzj.idempotent.anno.Idempotent;
import pers.xzj.idempotent.token.TokenHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description aspect
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-22 8:56
 * @Version 1.0.0
 */
@Aspect
@Component
public class IdempotentAspect {

    @Autowired
    private TokenHelper tokenHelper;

    @Pointcut("@annotation(pers.xzj.idempotent.anno.Idempotent)")
    public void pointcut() {

    }


    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        tokenHelper.checkToken(request);
    }

}
