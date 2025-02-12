package com.bd.grocy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.grocy.models.User;
import com.bd.grocy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserRepository userRepository;
	
	public List<User> allUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
}
