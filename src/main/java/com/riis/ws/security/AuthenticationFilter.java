package com.riis.ws.security;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.riis.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authManager;

	public AuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		System.out.println("###### Attempting Login ######");
		try {

			UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),
					UserLoginRequestModel.class);

			return authManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String email = ((User) auth.getPrincipal()).getUsername();
		res.addHeader("email", email);
		res.addHeader("Success", "Successfully Logged In");
//		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String key = "eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl";
		
		String token = Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, key).compact();

		res.addHeader("Authorization", token);

		System.out.println("$$$$$$$$$$$$$$$ SUCCESSFUL LOGIN $$$$$$$$$$$");

	}

}
