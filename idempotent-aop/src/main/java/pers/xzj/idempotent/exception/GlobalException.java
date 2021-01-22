package pers.xzj.idempotent.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description globalexception
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-19 14:25
 * @Version 1.0.0
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IdempotentException.class)
    public String idempotentException(IdempotentException e) {
        return e.getMessage();
    }
}
