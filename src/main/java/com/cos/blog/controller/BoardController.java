package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;


// 컨트롤러에서 세션에 접근하는 방법
// @AuthenticationPrincipal PrincipalDetail principal
// @AuthenticationPrincipal 어노테이션 => 컨트롤러에서 세션에 접근한다.
// System.out.println("로그인 사용자 아이디 : "+principal.getUsername());

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3, sort ="id", direction = Sort.Direction.DESC) Pageable pageable) {
		// main 페이지로 갈 때 데이터를 가져가야 함
		// 데이터를 뷰로 가져가기 위해서 Model을 사용한다.
		
		model.addAttribute("boards", boardService.글목록(pageable));
		// model의 이름이 boards 라는 이름으로 boardService.글목록()이 담겨져서 index.jsp로 데이터가 넘겨진다.
		// jstl 태그라이브러리를 이용해서 ${boards}를 하면 데이터를 받을 수 있다.
		
		return "index"; // boards라는 모델이 index로 이동한다. 
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
