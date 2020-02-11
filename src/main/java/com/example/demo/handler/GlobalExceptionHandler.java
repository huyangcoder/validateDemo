package com.example.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.constant.Code;
import com.example.demo.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Response<String> MyExceptionHandler(RuntimeException re) {
		logger.error("current Exception is {}", re.getMessage());
		Response<String>  response = new Response<String>();
		response.setMsg("请求失败");
		response.setCode(Code.FAILED);
		return response;
	}
	
	/**
	 * 字段验证
	 * */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<String> bindExceptionErrorHandler(MethodArgumentNotValidException ex) throws Exception {
         logger.error("bindExceptionErrorHandler info:{}",ex.getMessage());
         Response<String> r = new Response<>();
         StringBuilder sb = new StringBuilder();
         FieldError fieldError = ex.getBindingResult().getFieldError();
         sb.append(fieldError.getDefaultMessage());
         r.setMsg(sb.toString());
         r.setCode(Code.FAILED);
         return r;
    }

	/**
	 * 请求类型验证
	 */
	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	@ResponseBody
	public Response<String> mediaTypeExceptionHandler(HttpMediaTypeNotSupportedException e)throws Exception{
		logger.error("mediaTypeExceptionHandler info:{}",e.getMessage());
		Response<String> r = new Response<>();
		r.setMsg(String.format("this is not supporting media type"));
		r.setCode(Code.FAILED);
		return r;
	}
}
