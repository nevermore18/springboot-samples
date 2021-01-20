package pers.xzj.idempotent.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.xzj.idempotent.anno.Idempotent;
import pers.xzj.idempotent.token.TokenHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description interceptor
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-20 14:31
 * @Version 1.0.0
 */
@Component
public class IdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    TokenHelper tokenHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))
            return true;
        Method method = ((HandlerMethod) handler).getMethod();
        Idempotent idempotent = method.getDeclaredAnnotation(Idempotent.class);
        if (idempotent != null) {
            return tokenHelper.checkToken(request);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
