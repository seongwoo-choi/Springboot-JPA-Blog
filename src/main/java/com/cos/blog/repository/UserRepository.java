package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


//DAO
//자동으로 bean등록이 된다.
//@Repository를 생략할 수 있다.
public interface UserRepository extends JpaRepository<User, Integer>{
	//extends JpaRepository<User, Integer> 이게 뭐지??
	//해당 JpaRepository는 User 테이블이 관리하는 Repository이고 이 User 테이블의 Primary Key 는 Integer(숫자)이다.
	//기본적인 CRUD를 하고 싶으면 여기에 더 쓸 필요가 없다.

}
