package pers.xzj.idempotent.exception;

/**
 * @Description exception
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-19 14:20
 * @Version 1.0.0
 */
public class IdempotentException extends RuntimeException{
    public IdempotentException(String msg) {
        super(msg);
    }
}
