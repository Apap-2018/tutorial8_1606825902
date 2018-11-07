package com.apap.tutorial8.service;

import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	void updatePass(String userName, String newpassword, String oldpassword);
	UserRoleModel findByUserNameAndPassword(String username, String password);
}
