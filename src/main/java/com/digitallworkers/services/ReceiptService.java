package com.digitallworkers.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitallworkers.domain.Receipt;
import com.digitallworkers.repositories.ReceiptRepository;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;

	public Receipt criarComprovante(Receipt obj) {
		receiptRepository.save(obj);
		return obj;
	}

	public List<Receipt> findAll() {
		return receiptRepository.findAll();
	}

	public Optional<Receipt> find(Integer id) {
		Optional<Receipt> obj = receiptRepository.findById (id);
		return obj;

	}
	
}
