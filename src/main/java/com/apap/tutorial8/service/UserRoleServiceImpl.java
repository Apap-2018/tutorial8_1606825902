package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDB;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleDB userDb;
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		// TODO Auto-generated method stub
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel findByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userDb.findByUsernameAndPassword(username, password);
	}

	@Override
	public void updatePass(String userName, String newpassword, String oldpassword) {
		// TODO Auto-generated method stub
		UserRoleModel user = this.findByUserNameAndPassword(userName, oldpassword);
		String pass = encrypt(newpassword);
		user.setPassword(pass);
		userDb.save(user);
	}
}
