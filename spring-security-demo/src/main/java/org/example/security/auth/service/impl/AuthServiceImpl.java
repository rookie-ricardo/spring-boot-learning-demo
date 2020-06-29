package org.example.security.auth.service.impl;

import org.example.security.auth.bo.AccessToken;
import org.example.security.auth.cache.Cache;
import org.example.security.auth.constant.CacheName;
import org.example.security.auth.entity.UserDetail;
import org.example.security.auth.provider.AuthProvider;
import org.example.security.auth.provider.JwtProvider;
import org.example.security.auth.result.ApiResult;
import org.example.security.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager2;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private Cache caffeineCache;


    @Override
    public ApiResult login(String loginAccount, String password) {

        System.out.println("login");
        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
        // 2 认证
        Authentication authentication = this.authenticationManager2.authenticate(usernameAuthentication);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4 生成自定义token
        AccessToken accessToken = jwtProvider.createToken((UserDetails) authentication.getPrincipal());

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // 放入缓存
        caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
        return ApiResult.ok(accessToken);
    }

    @Override
    public ApiResult logout() {
        SecurityContextHolder.clearContext();
        caffeineCache.remove(CacheName.USER, AuthProvider.getLoginAccount());
        return ApiResult.ok();
    }

    @Override
    public ApiResult refreshToken(String token) {
        AccessToken accessToken = jwtProvider.refreshToken(token);
        UserDetail userDetail = caffeineCache.get(CacheName.USER, accessToken.getLoginAccount(), UserDetail.class);
        caffeineCache.put(CacheName.USER, accessToken.getLoginAccount(), userDetail);
        return ApiResult.ok(accessToken);
    }
}
