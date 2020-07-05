package org.example.security.auth.provider;

import org.example.security.auth.entity.UserDetail;
import org.example.security.auth.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class AuthProvider {
    public static UserDetail getUserDetail() {
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static UserInfo getUserInfo() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserInfo();
    }

    public static String getLoginAccount() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static String getUserId() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }

    public static List<String> getAuthorities() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles();
    }
}
