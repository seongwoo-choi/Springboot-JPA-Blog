package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. IoC를 해준다는 것.
// 서비스가 필요한 이유 : 1 트랜잭션
@Service
public class UserService {
	

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	

	// 이 회원 가입 서비스 전체가 하나의 트랜잭션이 된다.
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 1234
		String encPassword = encoder.encode(rawPassword); // 암호 해쉬화 
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);	
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) { // 웹(클라이언트)의 요청으로부터 받은 데이터 
		// 수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주기 때문이다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		}); // 영속화된 객체 persistance 생성
		String rawPassword = user.getPassword(); // password를 암호화해서 넣어야 된다.
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
			
		//회원 수정 메서드 종료시 = 서비스 종료 = 트랜잭션이 종료 = commit이 자동으로 완료(user 객체가 영속성 컨텍스트에 있기 때문)
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려
	}
	

	
}
