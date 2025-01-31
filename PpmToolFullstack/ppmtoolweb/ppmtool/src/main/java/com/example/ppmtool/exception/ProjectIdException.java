package com.example.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{

	public static final long serialversionId = 1L;
	
	public ProjectIdException(String message) {
		super(message);
	}
}
