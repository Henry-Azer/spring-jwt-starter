package com.henry.jwt.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userRes = userRepo.findByEmail(email);
//        if (userRes.isEmpty())
//            throw new UsernameNotFoundException("Could not findUser with email = " + email);
//        User user = userRes.get();


        return new org.springframework.security.core.userdetails.User(
                email,
                "$2a$12$zWSv4tYZU0Rg2IsuLV2b3.bBSEPJu8tkgi4lTMJaKr.W2Q2IrvSVa",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}