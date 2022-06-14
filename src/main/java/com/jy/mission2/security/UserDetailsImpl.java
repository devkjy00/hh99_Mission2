package com.jy.mission2.security;

import com.jy.mission2.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static com.jy.mission2.security.jwt.JwtTokenUtils.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final String email;
    private final String nickname;
    private final Long id;
    private final String password;

    public UserDetailsImpl(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.id = user.getId();
        this.password = user.getPassword();
    }

    public UserDetailsImpl(Map<String, String> jwtData) {
        this.email = jwtData.get(CLAIM_USER_EMAIL);
        this.nickname = jwtData.get(CLAIM_USER_NICK);
        this.id = Long.parseLong(jwtData.get(CLAIM_USER_ID));
        this.password = null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}