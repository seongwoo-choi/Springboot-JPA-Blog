package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	// IoC 컨테이너에 있는 빈을 가져온다.
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email을 들고 있다.
		System.out.println("UserApiController : save 호출됨");
		
		// 실제로 DB에 insert를 하고 아래에서 return을 한다.
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result); // Http 통신 상태가 OK이면 status에 200이 찍히고 데이터에는 1이 찍힌다. 실패하면 0이 찍힌다.
																									// 자바오브젝트를 JSON으로 변환해서 리턴한다.
	}
}
