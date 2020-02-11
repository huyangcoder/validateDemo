package com.example.demo.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class Response<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T result;
	
	private String msg;
	
	private Integer code;
	
}
