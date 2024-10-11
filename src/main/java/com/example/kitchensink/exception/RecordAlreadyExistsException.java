package com.example.kitchensink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class RecordAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -1279490029922655832L;

	public RecordAlreadyExistsException(String message) {
		super(message);
	}

	public RecordAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
