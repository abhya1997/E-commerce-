package com.e_commerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.Payment;

@Repository
public interface PayementRepo extends JpaRepository<Payment, Long>{

}
