package com.riis.ws.exceptions;

public class BalanceServiceException extends RuntimeException{
	private static final long serialVersionUID = -6785953156855849493L;
	
	public BalanceServiceException(String message) {
		super(message);
	}

}
