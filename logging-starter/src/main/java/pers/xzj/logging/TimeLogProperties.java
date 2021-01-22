package pers.xzj.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description properties
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-22 14:18
 * @Version 1.0.0
 */
@ConfigurationProperties(prefix = "time.log")
public class TimeLogProperties {
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
