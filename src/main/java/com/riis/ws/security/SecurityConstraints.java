package com.riis.ws.security;

public class SecurityConstraints {
	public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CREATE_EMPLOYEE_URL = "/employees";
    public static final String TOKEN_SECRET = "SECRETT0K3N";
    
}
