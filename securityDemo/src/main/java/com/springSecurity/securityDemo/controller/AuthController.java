package com.springSecurity.securityDemo.controller;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springSecurity.securityDemo.TestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;

	private DataSource dataSource;

	public AuthController(AuthService authService, DataSource dataSource) {
		this.authService = authService;
		this.dataSource = dataSource;
	}

	@PostMapping("/login")
	public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		authService.authentication(httpServletRequest, httpServletResponse);
	}
}
