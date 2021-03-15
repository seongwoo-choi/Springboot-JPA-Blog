package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//컨트롤러 라는 어노테이션이 붙으면 보통 File 을 리턴한다.
		//보통 파일리턴 경로 : src/main/resources/static 이 경로가 기본 경로이고
		//즉 컨트롤러는 해당 경로 아래에 있는 파일을 리턴해준다.
		//이 폴더에 리턴값에 해당하는 파일을 불러온다.
		//리턴명 : /home.html
		//풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// application.yml 을 보면 아래와 같이 설정되어 있고
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		// 이것은 리턴값 앞 뒤로 저 경로명을 붙여주는 것을 뜻한다.
		// 풀네임 :  /WEB-INF/views/test.jsp 로 경로가 시작되어서 브라우저에 출력된다.
		return "test";
	}
}
