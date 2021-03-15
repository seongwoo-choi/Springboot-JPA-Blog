package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
<<<<<<< HEAD
=======
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
<<<<<<< HEAD

=======
	
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		// HTTP 요청의 body 내용을 자바 객체로 맵핑하는 역할을 하는 @RequestBody 어노테이션
		// 그래서 User타입의 user는 username, password, email을 들고 있다.
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return을 한다.
		// 회원가입이 오류가 나면 GlobalExceptionHandler로 넘어가게 된다.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) { // key=value, x-www-form-urlencoded
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해 줄 것임
		// 강제로 세션값을 변경
		// 세션 등록

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
<<<<<<< HEAD

=======
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){ // @RequestBody를 안쓰면 JSON 데이터를 받지 못한다. 만약 @RequestBody를 안쓰면 key=value 값으로 데이터를 받는다.
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해 줄 것임
		// 강제로 세션값을 변경
		// 세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),  user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
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
	
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
}
