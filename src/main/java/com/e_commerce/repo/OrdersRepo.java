package com.e_commerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
