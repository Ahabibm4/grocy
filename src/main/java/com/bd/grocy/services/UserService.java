package com.bd.grocy.services;

import java.util.List;
import java.util.Set;

import com.bd.grocy.models.RoleName;
import com.bd.grocy.models.User;

public interface UserService {
	public User createUser(String username, String password, Set<RoleName> roleNames);
	
	public List<User> allUsers();
}
