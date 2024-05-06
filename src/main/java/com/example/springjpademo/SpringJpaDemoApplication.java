package com.example.springjpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}

	@GetMapping(value="/")
	public String helloWorld(){
		return "Hello world";
	}
}