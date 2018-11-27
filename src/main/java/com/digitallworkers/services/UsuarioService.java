package com.digitallworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitallworkers.domain.User;
import com.digitallworkers.repositories.UserRepository;

@Service
public class UsuarioService {

 @Autowired
 private UserRepository userRepository;
 
 public User criarUser(User obj) {
	 return userRepository.save(obj);
 }
}
