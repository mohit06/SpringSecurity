package com.demoSec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Spring security rocks!!";}
	
	@GetMapping("/buy")
	public String buy() {
		return "get lost!!";}
	
}
