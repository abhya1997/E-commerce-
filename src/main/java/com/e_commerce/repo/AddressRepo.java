package com.e_commerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{

}
