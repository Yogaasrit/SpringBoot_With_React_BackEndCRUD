package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);	}
	
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userRepository.findAll();	}
	
	@PutMapping("/edit/{id}")
	User editUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id).map(user -> {
			user.setUsername(newUser.getUsername());
			user.setName(newUser.getName());
			user.setName(newUser.getEmail());
			return userRepository.save(user);
		}).orElseThrow();
	}
	
	@GetMapping("/GetUserById/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow();
	}
	
	@DeleteMapping("/DeleteById/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
