package com.digitallworkers.services;

import java.util.List;

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

}
