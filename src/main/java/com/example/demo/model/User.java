package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {

	@NotBlank(message = "名字不该为空")
	private String userName;
	
	private String age;
}
