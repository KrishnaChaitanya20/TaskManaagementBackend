package me.dkcdev.task_management_api.common;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import me.dkcdev.task_management_api.users.models.User;

public class UserDetailsImpl implements UserDetails {

    private User user;
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public UUID getUserID(){
        return user.getUserId();
    }

    public UUID getOrganizationID(){
        return user.getOrganization().getOrganizationId();
    }

    public User getUser(){
        return this.user;
    }
    
    
}
