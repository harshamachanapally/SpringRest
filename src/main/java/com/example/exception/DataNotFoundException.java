package com.example.exception;

import org.springframework.stereotype.Repository;


public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public DataNotFoundException(String message){
		super(message);
	}

}
