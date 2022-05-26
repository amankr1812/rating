package com.origami.rating.controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/origami")
public class Controller {
	

	@GetMapping("/test") // Test function to return a string
	public String Test() {
		return "Hello World!";
		
		
	}
}
