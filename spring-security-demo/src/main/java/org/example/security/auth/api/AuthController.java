package org.example.security.auth.api;

import org.example.security.auth.bo.LoginInfo;
import org.example.security.auth.provider.JwtProvider;
import org.example.security.auth.result.ApiResult;
import org.example.security.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 认证
 * </p>
 *
 * @author 和耳朵
 * @since 2020-06-30
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ApiResult login(@Valid @RequestBody LoginInfo loginInfo) {
        return authService.login(loginInfo.getLoginAccount(), loginInfo.getPassword());
    }

    @PostMapping("/logout")
    public ApiResult logout() {
        return authService.logout();
    }

    @PostMapping("/refresh")
    public ApiResult refreshToken(HttpServletRequest request) {
        return authService.refreshToken(jwtProvider.getToken(request));
    }


}
