package org.example.security.auth.api;

import org.example.security.auth.bo.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/anon")
    public ApiResult test01() {
        return ApiResult.ok("匿名访问成功");
    }

    @GetMapping("/api/user")
    public ApiResult test02() {
        return ApiResult.ok("USER用户访问成功");
    }

    @GetMapping("/api/admin")
    public ApiResult test03() {
        return ApiResult.ok("管理员用户访问成功");
    }
}
