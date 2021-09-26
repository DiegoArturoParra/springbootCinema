package com.edu.cundi.cinema.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConflictException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConflictException(String message) {
		super(message);
	}

}
