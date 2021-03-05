package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	@GetMapping({"", "/"})
	public String index() { // 컨트롤러에서 세션에 접근하는 방법
		// @AuthenticationPrincipal PrincipalDetail principal
		// @AuthenticationPrincipal 어노테이션 => 컨트롤러에서 세션에 접근한다.
		// System.out.println("로그인 사용자 아이디 : "+principal.getUsername());
		return "index";
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
