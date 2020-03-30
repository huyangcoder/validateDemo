package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SpringBootApplication
public class ValidateDemoApplication {

	public static void main(String[] args) throws UnsupportedEncodingException {
		SpringApplication.run(ValidateDemoApplication.class, args)
	}

}
