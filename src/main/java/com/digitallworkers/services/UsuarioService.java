package com.digitallworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitallworkers.domain.User;
import com.digitallworkers.repositories.UserRepository;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private UserRepository userRepository;

	public User criarUser(User obj) {
		obj.setPassword(pe.encode(obj.getPassword()));
		return userRepository.save(obj);
	}
}
