package com.cos.blog.controller.api;



import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { 
		// HTTP 요청의 body 내용을 자바 객체로 맵핑하는 역할을 하는 @RequestBody 어노테이션
		// 그래서 User타입의 user는 username, password, email을 들고 있다.
		System.out.println("UserApiController : save 호출됨");
		
		// 실제로 DB에 insert를 하고 아래에서 return을 한다.
		user.setRole(RoleType.USER);
		// 회원가입이 오류가 나면 GlobalExceptionHandler로 넘어가게 된다.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // Http 통신 상태가 OK이면 status에 200이 찍히고 데이터에는 1이 찍힌다. 실패하면 0이 찍힌다.
																									// 자바오브젝트를 JSON으로 변환해서 리턴한다.
	}
	
	
	// 전통적인 방식의 로그인 시큐리티 사용 X
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//		
//		System.out.println("UserApiController : login 호출됨");
//		User principal = userService.Login(user);  // principal 접근 주체라는 용어 
//		
//		if(principal != null) {
//			session.setAttribute("principal",  principal);
//		}
//	
//		System.out.println(new ResponseDto<Integer>(HttpStatus.OK.value(), 1));
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
	// Security를 이용해서 로그인한다. 
	
}
