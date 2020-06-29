package org.example.security.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetail implements Serializable, UserDetails {
    private static final long serialVersionUID=1L;

    private UserInfo userInfo;
    private List<RoleInfo> roleInfoList;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private List<String> roles;

    public String getUserId(){
        return this.userInfo.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities");
        if (grantedAuthorities != null) return this.grantedAuthorities;
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> authorities = new ArrayList<>();
        roleInfoList.forEach(role -> {
            authorities.add(role.getRoleCode());
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
        });
        this.grantedAuthorities = grantedAuthorities;
        this.roles = authorities;
        System.out.println("getAuthorities complete");
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userInfo.getUsername();
    }

    /**
     * 账户是否没过期
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否没被锁定
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账户凭据是否没过期
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
