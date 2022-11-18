package com.capgemini.sprint.jobsearchapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnavailableException extends RuntimeException{
	private static final long serialVersionUID = -6279287724295317351L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
