package com.nextinno.doshare.security;

import com.nextinno.doshare.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rsjung on 2016-11-27.
 */
public class UserDetailsImpl extends org.springframework.security.core.userdetails.User{
    public UserDetailsImpl(User user) {
        super(user.getEmail(), user.getPassword(), authorities(user));
    }

    private  static Collection<? extends GrantedAuthority> authorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(!user.getRole().equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
