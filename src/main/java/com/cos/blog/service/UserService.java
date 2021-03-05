package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
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
	
}