package org.example.security.auth.provider;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.example.security.auth.bo.AccessToken;
import org.example.security.auth.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * JWT组件
 * </p>
 * <p>
 * 一个完整的JwtToken由三部分组成：头部+负载信息+签名
 * header 存放JwtToken签名的算法 | token的类型：{"alg": "HS512","typ": "JWT"}
 * payload 主要存放用户名、创建时间、生成时间：{"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature 生成算法：HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * <p>
 * Created by zhangyanqi
 */
@Slf4j
@Component
public class JwtProvider {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 从请求中拿到token
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader(jwtProperties.getRequestHeader());
    }

    /**
     * 根据用户信息生成token
     */
    public AccessToken createToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    /**
     * 根据用户信息生成token
     */
    public AccessToken createToken(String subject) {
        // 当前时间
        final Date now = new Date();
        // 过期时间
        final Date expirationDate = new Date(now.getTime() + jwtProperties.getExpirationTime() * 1000);

        String token = jwtProperties.getTokenPrefix() + Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getApiSecretKey())
                .compact();
        return AccessToken.builder().token(token).expirationTime(expirationDate).build();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject().equals(userDetails.getUsername()) && !isTokenExpired(claims);
    }


    /**
     * 刷新token
     * 过滤器会对请求进行验证，所以这里可以不必验证
     *
     * @param oldToken 带tokenHead的token
     */
    public AccessToken refreshToken(String oldToken) {
        String token = oldToken.substring(jwtProperties.getTokenPrefix().length());

        // token反解析
        Claims claims = getClaimsFromToken(token);

        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(claims)) {
            return AccessToken.builder().loginAccount(claims.getSubject()).token(oldToken).expirationTime(claims.getExpiration()).build();
        } else {
            return createToken(claims.getSubject());
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     */
    private boolean tokenRefreshJustBefore(Claims claims) {
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(claims.getExpiration()) && refreshDate.before(DateUtil.offsetSecond(claims.getExpiration(), 1800))) {
            return true;
        }
        return false;
    }

    /**
     * 从token中拿到负载信息
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getApiSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT反解析失败, token已过期或不正确, token : {}", token);
        }
        return claims;
    }


    /**
     * 从token中获取主题
     */
    public String getSubjectFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims != null) {
            return claims.getSubject();
        } else {
            return null;
        }
    }


    /**
     * 判断token是否已经过期
     */
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }


}
