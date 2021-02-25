package com.cos.blog.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController //데이터만응답해주게 해준다.
public class DummyControllerTest {
	
	//의존성 주입!!
	@Autowired //Autowired 어노테이션을 사용하면 DummyControllerTest가 메모리에 뜰 때 UserRepository 얘도 같이 메모리에 뜨게 된다.
	private  UserRepository UserRepository;

	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 요청하게 되면
	//파라미터에 쏙쏙 들어오게 된다.
	@PostMapping("/dummy/join")
	public String join(User user){ 
		//x-www-form-urlencoded 방식으로 전송되는 데이터는 key=value 형태로 보내지고 String username, String password, String email 에 값이 들어가진다. (spring이 해주는 약속된 규칙)
		System.out.println("id :"+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+ user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER); //Default 값을 user로 고정
		
		//save 가 될 때 getCreationDate에서 어노테이션이 작동해서 값이 null에서 변경이된다.
		//role이 null 값으로 들어가는 이유는 무엇일까?
		//Default Value가 user이고 인서트할 때 null인 녀석을 제외하고 실행을 시켜줘야 하는데 그렇지 못했기 때문이다.
		UserRepository.save(user); 
		return "회원가입이 완료되었습니다.";
	}
	
}
