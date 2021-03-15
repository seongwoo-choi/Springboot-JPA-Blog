package com.cos.blog.controller;

<<<<<<< HEAD
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
=======
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetail;
<<<<<<< HEAD
=======

>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/* 이하 경로만 허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 /js/**, /css/**, image/** 허용
//인증이 필요없는 경로

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallbakc(String code) { // @ResponseBody => 데이터를 리턴해주는 컨트롤러 함수
		
		// POST 방식으로 key=value 타입의 데이터를 카카오쪽으로 요청을 해야 한다.
		// <a href=""> 방식의 요청은 GET 요청방식이므로 RestTemplate 라이브러리를 사용하여
		// POST 방식으로 데이터를 전송한다.(Retrofit2:안드로이드에서 많이 사용, OkHttp, RestTemplate)
		RestTemplate rt = new RestTemplate();
		// 헤더를 생성 
		HttpHeaders headers = new HttpHeaders();
		// 헤더에 컨텐트 타입을 담는다 => 내가 전송할 데이터가 key=value 형태의 데이터라고 알려주는 것 
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// 내 바디 데이터를 담을 MultiValueMap을 생성 => HttpBody 오브젝트 생성 
		// 사실 변수화해서 넣는 것이 제일 좋은 방법이다...
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "1ea195593eda87047dc90705d26f7ef7");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		 
		// 바디 데이터와 헤더 값을 가지고 있는 엔티티를 생성한다.
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담는다.
		// exchange라는 함수는 HttpEntity 값을 받기 때문이다.
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답을 받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST, 
				kakaoTokenRequest, // 바디 데이터와 헤더 값을 적는다.
				String.class // 응답을 받을 타입을 적는다. => response의 응답이 String으로 온다.
		);
		
		// String code로 쿼리 스트링 값인 인증 코드를 가져올 수 있다.
		// 코드 값을 받았기 때문에 인증 완료가 되었고 인증된 코드값을 통해서
		// accessToken을 받아서 로그인하는 사람의 개인정보를 응답받는다.
		// 개인 정보에 접근하기 위한 토큰을 부여받는다.
		return "카카오 토큰 요청 완료 : 토큰 요청에 대한 응답 " + response;
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm() {	
	  return "user/updateForm";
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
