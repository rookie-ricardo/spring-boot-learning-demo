package org.example.security.auth.api;

import org.example.security.auth.result.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/anon")
    public ApiResult test01() {
        return ApiResult.ok("匿名访问成功");
    }

    @GetMapping("/user")
    public ApiResult test02() {
        return ApiResult.ok("登录用户访问成功");
    }

    @GetMapping("/admin")
    public ApiResult test03() {
        return ApiResult.ok("登录用户访问成功");
    }
}
