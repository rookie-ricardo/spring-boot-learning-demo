package org.example.security.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 密匙
     */
    private String apiSecretKey = "JWT_SECRET_KEY";

    /**
     * 过期时间-默认半个小时
     */
    private Long expirationTime = 1800L;

    /**
     * 默认存放token的请求头
     */
    private String requestHeader = "Authorization";

    /**
     * 默认token前缀
     */
    private String tokenPrefix = "Bearer ";
}
