package com.sms.repositories;


import com.sms.entities.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository  extends JpaRepository<Fee,Long>{
}
