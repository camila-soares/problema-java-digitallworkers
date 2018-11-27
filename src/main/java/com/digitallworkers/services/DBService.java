package com.digitallworkers.services;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitallworkers.domain.Receipt;
import com.digitallworkers.domain.User;
import com.digitallworkers.repositories.ReceiptRepository;
import com.digitallworkers.repositories.UserRepository;


@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ReceiptRepository receiptRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void instatiateTestDatabase() throws ParseException{

		Receipt r1 = new Receipt(null, "Informática", "Computador", "25/11/2018");
		Receipt r2 = new Receipt(null, "Filmes", "Series", "25/12/2018");
		
		User user1 = new User(null, "Camila", "camila@gmail.com", "123");
		
		receiptRepository.save(r1);
		receiptRepository.save(r2);
		
		userRepository.save(user1);
	}
}
