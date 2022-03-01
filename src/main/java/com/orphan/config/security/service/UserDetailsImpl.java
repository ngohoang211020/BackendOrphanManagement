package com.orphan.config.security.service;

import com.orphan.common.entity.user.Role;
import com.orphan.common.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user){
        this.user=user;
    }

    public Integer getId(){
        return user.getId();
    }

    public String getPhone(){
        return user.getPhone();
    }
    public String getFullName(){
        return user.getFullName();
    }
    public String getIdentification(){
        return user.getIdentification();
    }
    public String getImage(){
        return user.getImage();
    }
    public String password(){
        return user.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles=user.getRoles();

        List<SimpleGrantedAuthority> authorities=new ArrayList<>();

        for(Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
