package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


//DAO
//자동으로 bean등록이 된다.
//@Repository를 생략할 수 있다.
public interface UserRepository extends JpaRepository<User, Integer>{
	//extends JpaRepository<User, Integer> 이게 뭐지??
	//해당 JpaRepository는 User 테이블이 관리하는 Repository이고 이 User 테이블의 Primary Key 는 Integer(숫자)이다.
	//기본적인 CRUD를 하고 싶으면 여기에 더 쓸 필요가 없다.
	
	// 로그인을 위한 함수를 만들어보자 1
	// JPA Naming 쿼리 전략이 있다.
	// SELECT * FROM user WHERE username = ?1 AND password = ?2;
	// ? 값에 String username,  String password 가 들어간다.
	User findByUsernameAndPassword(String username, String password);
	
	// 로그인을 위한 함수를 만들어보자 2
	// UserRepository.login() 함수를 실행하면 value 쿼리문이 실행되고, User 객체를 리턴해준다.
//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User Login(String username, String password);


}
