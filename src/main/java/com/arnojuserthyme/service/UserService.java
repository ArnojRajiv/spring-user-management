package com.arnojuserthyme.service;
import java.util.List;

import com.arnojuserthyme.model.UserDtls;



public interface UserService {
	public UserDtls createuser(UserDtls user);
	public boolean checkEmail(String email);
	public List<UserDtls> findAdmin();
	UserDtls getUserById(int id);
	void updateuser(int id , UserDtls updateduser);
	void deleteUser(int id);
	
}
