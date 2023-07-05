package com.arnojuserthyme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arnojuserthyme.model.UserDtls;


public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	public boolean existsByEmail(String email);

	public UserDtls findByEmail(String email);
	
	 @Query("SELECT u FROM UserDtls u WHERE u.role = 'ROLE_ADMIN'")
	  public  List<UserDtls> findAllAdminUsers(); 
	
}