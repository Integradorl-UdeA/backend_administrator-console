package com.consola.lis.jwt;

import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {
    private final UserLis userLis;
    private final UserHelloLis userHelloLis;

    public CustomUserDetails(UserLis userLis, UserHelloLis userHelloLis) {
        this.userLis = userLis;
        this.userHelloLis = userHelloLis;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userLis.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userHelloLis.getPassword();
    }

    @Override
    public String getUsername() {
        return userLis.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
