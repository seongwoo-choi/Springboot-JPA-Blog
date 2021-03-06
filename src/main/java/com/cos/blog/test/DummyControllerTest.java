package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

//import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	// 의존성 주입(DI)
	@Autowired //Autowired 어노테이션을 사용하면 DummyControllerTest가 메모리에 뜰 때 UserRepository 얘도 같이 메모리에 뜨게 된다.
	private UserRepository userRepository;
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	// email, password
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id : "+id;
	}
	
	// email, password을 수정할 것이다.
	// Transactional 어노테이션은 함수가 종료 시에 자동으로 commit이 됨.
	@Transactional // 함수 종료시에 자동 commit 이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줘요.)
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		// if 2번 아이디가 왔을 경우 2번 아이디를 셀렉트 한 것이기 때문에 영속화가 된다.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		//user.setEmail(requestUser.getEmail());
		
		// userRepository.save(user);
		
		// UserRepository는 User 테이블의 CRUD를 해주는 DAO 같은 녀석
		// save 함수는 id를 전달하지 않으면 insert를 해주고 
		// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
		// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
		// UserRepository.save(user); 
		
		
		// 더티 체킹
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	// DB에 저장된 모든 데이터들을 갖고 온다.
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);

		List<User> users = pagingUser.getContent(); // getContent()를 사용하면 List타입으로 변환해서 받을 수 있다.
		return pagingUser;
	}
	
	// {id} 주소로 파마레터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		// .get()이오면 null 절대 올리가 없다라는 뜻
		// select한 값이 null이 리턴되는 경우에 아래와 같이 쓰면 된다.
       // 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다.");
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}

	// http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 요청하게 되면
	//파라미터에 쏙쏙 들어오게 된다.
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (약속된 규칙)
		//x-www-form-urlencoded 방식으로 전송되는 데이터는 key=value 형태로 보내지고 String username, String password, String email 에 값이 들어가진다. (spring이 해주는 약속된 규칙)
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER); //Default 값을 user로 고정
		
		//save 가 될 때 getCreationDate에서 어노테이션이 작동해서 값이 null에서 변경이된다.
		//role이 null 값으로 들어가는 이유는 무엇일까?
		//Default Value가 user이고 인서트할 때 null인 녀석을 제외하고 실행을 시켜줘야 하는데 그렇지 못했기 때문이다.
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
