package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DemoUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getLoginId(), user.getPassword(),
                AuthorityUtils.createAuthorityList("ADMIN", "USER"));
    }
}
