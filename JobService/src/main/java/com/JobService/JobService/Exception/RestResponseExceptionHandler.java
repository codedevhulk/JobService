package com.JobService.JobService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.JobService.JobService.response.ErrorMessage;

@ControllerAdvice
public class RestResponseExceptionHandler {

	@ExceptionHandler(CustomException.class)
	@ResponseBody()
	@ResponseStatus()
	public ResponseEntity<ErrorMessage> CustomExceptionHandler(CustomException exception) {
		ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(),exception.getErrorCode());
		return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getStatus()));
	}
	
}
	
	

	
	

