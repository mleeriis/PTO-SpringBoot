package com.riis.ws.exceptions;

import com.riis.ui.model.response.ErrorMessages;

public class UserServiceException extends RuntimeException {
	private static final long serialVersionUID = 4966220070813286459L;
	
	public UserServiceException(String message) {
		super(message);
	}

}
