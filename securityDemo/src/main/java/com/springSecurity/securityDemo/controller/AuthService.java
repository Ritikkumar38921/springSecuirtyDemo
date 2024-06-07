package com.springSecurity.securityDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.springSecurity.securityDemo.TestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {
	
	private AuthenticationManager authManager;
	private TestService testService;

	public AuthService(AuthenticationManager authManager, TestService testService) {
		this.authManager = authManager;
		this.testService = testService;
	}

	public void authentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
		usernamePasswordAuthenticationFilter.setAuthenticationManager(authManager);
		Authentication authentication = usernamePasswordAuthenticationFilter.attemptAuthentication(httpServletRequest,
				httpServletResponse);
		if (authentication.isAuthenticated()) {
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
			securityContext.setAuthentication(authentication);
			
			testService.answer(5);
		} else {
			System.out.println("not authenticated");
		}
	}

}
