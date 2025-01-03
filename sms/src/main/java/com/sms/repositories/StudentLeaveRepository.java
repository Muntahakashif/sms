package com.sms.repositories;


import com.sms.entities.StudentLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave,Long> {
    List<StudentLeave> findByUserId(Long studentId);

    List<StudentLeave> findAllByUserId(Long studentId);
}