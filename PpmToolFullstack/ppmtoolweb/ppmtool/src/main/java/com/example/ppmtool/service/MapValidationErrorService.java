package com.example.ppmtool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {

	public ResponseEntity<?> mapValidationError(BindingResult result){
		if(result.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
				System.out.println(fieldError.getField()+" fieldError.getDefaultMessage()");
			}
			return new ResponseEntity<List<FieldError>>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
