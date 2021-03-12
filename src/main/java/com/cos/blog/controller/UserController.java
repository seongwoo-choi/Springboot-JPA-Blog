package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/* 이하 경로만 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, image/** 허용
// 인증이 필요없는 경로

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
<<<<<<< HEAD
	// principal은 AuthenticationPrincipal 객체를 가져온다.
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
=======
	public String updateForm() {
>>>>>>> be8386b914d7722fa601a3c71114ad5334248cb2
		return "user/updateForm";
	}
	
}
