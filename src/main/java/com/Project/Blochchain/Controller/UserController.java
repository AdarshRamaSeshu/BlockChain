package com.Project.Blochchain.Controller;

//import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.Blochchain.Entity.User;
import com.Project.Blochchain.Service.UserService;
//import com.Project.Blochchain.Service.WalletService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private UserService user_service;
	

	@PostMapping("/addAdmin")
	public void addAdmin(@RequestBody User admin) {
		
		 user_service.addAdmin(admin);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		User addeduser=user_service.addUser(user);
		
		if(addeduser == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addeduser);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addeduser);
	}
	
	/*
	@GetMapping("/getUsers")
	public List<User> getUers(){
		return user_service.getUsers();
	}
	
	@GetMapping("/getAdmins")
	public List<User> getAdmin(){
		return user_service.getAllAdmins();
	}
	*/
}
