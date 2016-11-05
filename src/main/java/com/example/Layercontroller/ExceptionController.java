package com.example.Layercontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.exception.DataNotFoundException;
import com.example.exception.DuplicateRecordException;
import com.example.exception.ErrorMessage;
import com.example.exception.OutOfStockException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorMessage> DataException(DataNotFoundException D){
		
		ErrorMessage errorMessage = new ErrorMessage();
		// 404 status
		errorMessage.setErrorCode(HttpStatus.NOT_FOUND.toString());
		errorMessage.setErrorMessage(D.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<ErrorMessage> DuplicateException(DuplicateRecordException D){
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.toString());
		errorMessage.setErrorMessage(D.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<ErrorMessage> OutOfStockException(OutOfStockException D){
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.CONFLICT.toString() );
		errorMessage.setErrorMessage(D.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.CONFLICT);
	}
	
}
