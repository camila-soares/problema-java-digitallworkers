package com.digitallworkers.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitallworkers.domain.Receipt;
import com.digitallworkers.services.ReceiptService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

	@Autowired
	private ReceiptService service;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> insert(@Valid @RequestBody Receipt obj){
		obj = service.criarComprovante(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Receipt>> findAll() throws ObjectNotFoundException {
		List<Receipt> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
}
