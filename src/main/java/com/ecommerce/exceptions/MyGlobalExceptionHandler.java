package com.ecommerce.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.payload.APIResponse;



@RestControllerAdvice
public class MyGlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response,
                HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity <Map<String , Object> > myResourceNotFoundException(ResourceNotFoundException e){
		Map<String , Object> response = new HashMap<>();
		response.put("timestamp", e.getTimestamp());
		response.put("message", e.getMessage());
		response.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<> (response , HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(APIException.class)
	public ResponseEntity<APIResponse> myAPIException(APIException e){
		String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
		return new ResponseEntity<APIResponse> (apiResponse, HttpStatus.BAD_REQUEST);
		
	}
}
