package pers.xzj.idempotent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xzj.idempotent.anno.Idempotent;
import pers.xzj.idempotent.token.TokenHelper;
import pers.xzj.logging.TimeLog;

/**
 * @Description hello
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-19 14:16
 * @Version 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private TokenHelper tokenHelper;

    @GetMapping("/getToken")
    @TimeLog
    public String getToken() {
        return tokenHelper.createToken();
    }


    @PostMapping("/hello")
    @Idempotent
    public String hello(){return "hello";}

    @PostMapping("/hello2")
    public String hello2(){return "hello2";}

}
