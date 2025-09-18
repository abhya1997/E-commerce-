package com.e_commerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	//User findUserByusername(String username);
	@Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUserByusername(@Param("username") String username);
	
	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
	Optional<User> findByUsernameWithRoles(@Param("username") String username);


}
