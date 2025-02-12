package com.bd.grocy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bd.grocy.models.Role;
import com.bd.grocy.models.RoleName;
import com.bd.grocy.models.User;
import com.bd.grocy.repository.RoleRepository;
import com.bd.grocy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserRepository userRepository;

	@Autowired private RoleRepository roleRepository;

	@Autowired private PasswordEncoder passwordEncoder;

	public User createUser(String username, String password, Set<RoleName> roleNames) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		
		Set<Role> roles = roleNames.stream()
				.map(roleName -> roleRepository.findByName(roleName)
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
				.collect(Collectors.toSet());
		
		//user.setRoles(roles);
		return userRepository.save(user);
	}
	
	public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
