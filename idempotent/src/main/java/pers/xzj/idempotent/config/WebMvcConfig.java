package pers.xzj.idempotent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.xzj.idempotent.interceptor.IdempotentInterceptor;

/**
 * @Description mvc
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-20 14:45
 * @Version 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private IdempotentInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
