package com.riis.ws.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Authorization");

		if (header == null) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String key = "eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl" +
				"eyJhbGciOiJSUzI1NiIsImtpZCI6InMxIn0eyJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxl";
		
		if (token != null) {
			String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
			// TODO: Increase security by using an auto-generated key rather than a hard coded plain text key. 
			/* To do this, need to implement a SigningKeyResolver
			 * https://github.com/jwtk/jjwt#jws-read for more details on implementing a SigningKeyResolver
			 */


			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
