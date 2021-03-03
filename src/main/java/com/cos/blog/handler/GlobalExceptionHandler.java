package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 Exception을 여기서 처리한다.
@RestController
public class GlobalExceptionHandler {

	//IllegalArgumentException 예외처리만 다룬다.
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) { // Illegal 예외를 변수로 받아서 온다.
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
