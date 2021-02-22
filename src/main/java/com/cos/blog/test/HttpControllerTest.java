package com.cos.blog.test;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청했을 때 -> html응답해주면 @Controller 어노테이션을 사용한다.

//사용자가 요청 -> 데이터를 응답해준다.
@RestController
public class HttpControllerTest{
	
	private static final String TAG = "HttpControllerTest : ";
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Builder 어노테이션을 사용하면 내가 값을 넣을 때 생성자를 통한 순서를 지키지 않아도 상관이 없다.
		Member m =Member.builder().username("ssar").password("1234").email("ssar@naver.com").build();
		System.out.println(TAG + "getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get 요청 밖에 할 수 없다.
	// http:localhost:8080/http/get (select)
	//쿼리스트링을 @RequestParam 어노테이션을 이용해서 서버에서 받을 수 있다.
	//Member 객체를 사용해서 받아올 수 있다.
	@GetMapping("http/get")
	public String getTest(Member m) { //http://localhost:8080/http/get?id=1&username=ssar&password=1234&email=email@nate.com 
																//물음표 안에 있는 값들을 Member에 쏙 넣어주는 역할을 spring이 해준다
																//get 방식으로 요청할 때 어떤 데이터를 요청하는 방식은 쿼리스트링 밖에 없다.
		return "get 요청 : "+m.getId() +", "+m.getUsername()+", "+m.getPassword()+","+m.getEmail();
	}
	
	// http:localhost:8080/http/post (insert)
	@PostMapping("http/post")
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)
		 //postman에서 body -> raw타입으로 전송시에 @RequestBody String text로 전송
		 //즉, text/plain 타입으로 전송 할 때 사용한다.
		//return "post 요청 : " + text;
		
		//application/json 타입으로 전송 시
		//Member m 이라고 맵핑을 해놓으면 spring 자동으로 맵핑해준다.
		return "post 요청 : "+m.getId() +", "+m.getUsername()+", "+m.getPassword()+","+m.getEmail();
	}
	
	// http:localhost:8080/http/put (update)
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) { //RequestBody 어노테이션을 통해 오브젝트로 맵핑해서 받을 수 있다.
		return "put 요청 : "+m.getId() +", "+m.getUsername()+", "+m.getPassword()+","+m.getEmail();
	}
	
	// http:localhost:8080/http/delete (delete)
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
