package com.arnojuserthyme.service;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arnojuserthyme.model.UserDtls;
import com.arnojuserthyme.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public UserDtls createuser(UserDtls user) {

		user.setPassword(passwordEncode.encode(user.getPassword()));
		

		return userRepo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {

		return userRepo.existsByEmail(email);
	}

	@Override
	public List<UserDtls> findAdmin() {
		List<UserDtls> list = userRepo.findAllAdminUsers();
		
		return list;
	}

	@Override
	public UserDtls getUserById(int id) {
		Optional<UserDtls> optional = userRepo.findById(id);
		UserDtls userDtls = null;
		if(optional.isPresent())
		{
			userDtls = optional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id:: "+id);
		}
		return userDtls;
	}

	@Override
	public void updateuser(int id, UserDtls updateduser) {
		
		
			UserDtls existinguser = userRepo.findById(id).orElse(null);
			
			if(existinguser != null)
			{
				existinguser.setAddress(updateduser.getAddress());
				existinguser.setEmail(updateduser.getEmail());
				existinguser.setFullName(updateduser.getFullName());
				existinguser.setPassword(updateduser.getPassword());
				existinguser.setQualification(updateduser.getQualification());
				existinguser.setRole(updateduser.getRole());
				
				userRepo.save(existinguser);
			}
		
			return;
		
		
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
		
	}


	

	
	
	
	
}