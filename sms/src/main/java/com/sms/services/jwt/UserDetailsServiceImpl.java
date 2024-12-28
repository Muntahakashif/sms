package com.sms.services.jwt;


import com.sms.entities.User;
import com.sms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Fetch user from database
//        User user = userRepository.findFirstByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        // Return UserDetails object
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
//        );
        Optional<User> userOptional=userRepository.findFirstByEmail(email);
        if(userOptional.isEmpty()) throw new UsernameNotFoundException("User not found",null);
        return new org.springframework.security.core.userdetails.User(
                userOptional.get().getEmail(),
                userOptional.get().getPassword(),
                new ArrayList<>()
        );

    }
}
