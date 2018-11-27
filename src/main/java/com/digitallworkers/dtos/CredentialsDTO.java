package com.digitallworkers.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	

}
