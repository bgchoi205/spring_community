package com.cbg.exam.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {


    private Long id;
    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;
    private Collection<GrantedAuthority> AUTHORITY;

    public CustomUserDetails(Long id, String loginId, String loginPw, String name,
                             String nickname, String email, List<GrantedAuthority> authorities) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.AUTHORITY = authorities;
    }


    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AUTHORITY;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
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

}
