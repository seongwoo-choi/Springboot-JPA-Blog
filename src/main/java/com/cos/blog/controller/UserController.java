package com.cos.blog.controller;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetail;

import java.util.UUID;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/* 이하 경로만 허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 /js/**, /css/**, image/** 허용
//인증이 필요없는 경로

@Controller
public class UserController {

	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private UserService userService;


	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallbakc(String code) throws JsonProcessingException { // @ResponseBody => 데이터를 리턴해주는 컨트롤러 함수


		// POST 방식으로 key=value 타입의 데이터를 카카오쪽으로 요청을 해야 한다.
		// <a href=""> 방식의 요청은 GET 요청방식이므로 RestTemplate 라이브러리를 사용하여
		// POST 방식으로 데이터를 전송한다.(Retrofit2:안드로이드에서 많이 사용, OkHttp, RestTemplate)
		// 컨트롤러에서 POST 방식으로 데이터를 전송할 때 사용하는 것이 RestTemplate 이다.
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

		// Gson, Json Simple, ObjectMapper 이 셋 중에 하나를 이용해서 JSON을 담을 수 이싿.
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken =null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		}catch (JsonMappingException e){
			e.printStackTrace();
		}


		System.out.println("카카오 액세트 토큰 : " + oauthToken.getAccess_token());

		// 서비스에서 로직을 짜고 컨트롤러에 DI를 해야 좋은 방법인데
		// OAuth2.0을 연습하기 위해서 컨트롤러에 다 넣었다.
		RestTemplate rt2 = new RestTemplate();
		// 헤더를 생성
		HttpHeaders headers2 = new HttpHeaders();
		// 헤더에 Authorization을 담는다.
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		// 헤더에 컨텐트 타입을 담는다 => 내가 전송할 데이터가 key=value 형태의 데이터라고 알려주는 것
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// 바디 데이터와 헤더 값을 가지고 있는 엔티티를 생성한다.
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담는다.
		// exchange라는 함수는 HttpEntity 값을 받기 때문이다.
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답을 받음.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2, // 바디 데이터와 헤더 값을 적는다.
				String.class // 응답을 받을 타입을 적는다. => response의 응답이 String으로 온다.
		);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile =null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		}catch (JsonMappingException e){
			e.printStackTrace();
		}

		// User 오브젝트 : username, password, email
		System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
		System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());

		System.out.println("블로그 서버 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그 서버 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		// UUID garbagePassword = UUID.randomUUID();
		// UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
		System.out.println("블로그 서버 패스워드 : "+ cosKey);

		// 카카오 회원 정보를 이용해서 회원가입
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		// 가입자 인지 혹은 비가입자인지 체크를 해서 처리해야 한다.
		// 즉, 중복 가입인지 아닌지 체크를 해야한다.
		User originUser = userService.회원찾기(kakaoUser.getUsername());

		// 비가입자이면 회원가입을 한다.
		if(originUser.getUsername() == null){
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다.");
			userService.회원가입(kakaoUser);
		}
		// 로그인 처리(UserApiController의 로그인 처리와 같음)
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);


		// String code로 쿼리 스트링 값인 인증 코드를 가져올 수 있다.
		// 코드 값을 받았기 때문에 인증 완료가 되었고 인증된 코드값을 통해서
		// accessToken을 받아서 로그인하는 사람의 개인정보를 응답받는다.
		// 개인 정보에 접근하기 위한 토큰을 부여받는다.
		return "redirect:/";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm() {	
	  return "user/updateForm";
	}
	
}
