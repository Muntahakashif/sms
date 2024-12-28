package com.sms.services.User;

import com.sms.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByUserEmail(String email);
    Optional<User> findByName(String name);
}
