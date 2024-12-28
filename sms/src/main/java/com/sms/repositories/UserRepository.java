package com.sms.repositories;


import com.sms.dto.StudentDto;
import com.sms.entities.User;
import com.sms.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByRole(UserRole userRole);
    Optional<User> findFirstByEmail(String email);

    List<User>  findAllByRole(UserRole userRole);
    Optional<User> findByName(String name);
}
