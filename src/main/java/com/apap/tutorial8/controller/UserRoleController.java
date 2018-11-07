package com.apap.tutorial8.controller;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@Autowired
	private UserDetailsService userDetail;
	
	@RequestMapping(value ="/addUser", method=RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value ="/updatePass", method=RequestMethod.GET)
	private String updatePass() {
		return "UpdatPass";
	}
	
	@RequestMapping(value ="/updatePass", method=RequestMethod.POST)
	private String updatePassSubmit(@RequestParam("oldPass") String oldPass,
			@RequestParam("password") String password, @RequestParam("confPass") String confPass, 
			Model model, Principal principal) {
		String namaUser = principal.getName();
		UserDetails user = userDetail.loadUserByUsername(namaUser);
		userService.updatePass(user.getUsername(), password, user.getPassword());
		return "update";
	}
}
