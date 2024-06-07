package com.springSecurity.securityDemo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.securityDemo.TestService;

@RestController
@RequestMapping("/test")
//@RequestScope(proxyMode = "prototype")
public class TestContoller {

	@Autowired
	private TestService testService;
	
	@GetMapping("/")
	public void testData() {
		testService.answer(5);
	}
	
	
	
	
}
