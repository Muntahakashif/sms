package com.sms.services.User;


import com.sms.entities.User;
import com.sms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUserEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
