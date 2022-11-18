package com.capgemini.sprint.jobsearchapp.advice;

import java.util.LinkedHashMap;

import javax.servlet.UnavailableException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobsearchExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public LinkedHashMap<String, String> handleBardRequest(MethodArgumentNotValidException me){
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();		
		
		me.getFieldErrors().stream().forEach(fieldError -> {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
				
		return errors;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UnavailableException.class)
	public LinkedHashMap<String, String> handleUnavailable(UnavailableException e) {
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();	
		
		errors.put("empId",e.getMessage());
		return errors;
	}

}
