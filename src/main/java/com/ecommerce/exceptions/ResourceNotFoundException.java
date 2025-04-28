package com.ecommerce.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final LocalDateTime timestamp;

	public ResourceNotFoundException(String message) {
		
		super(message);
		this.timestamp = LocalDateTime.now();
	}

}
