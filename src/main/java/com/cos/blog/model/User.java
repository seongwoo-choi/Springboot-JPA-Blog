package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(다른언어 포함) Object -> 테이블로 맵핑해주는 기술!!! 변수명을 바꾸고 저장하면 DB도 바로 수정이 된다!!!
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id //Primary Key 가 된다는 것을 알려주는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // Oracle인 경우 시퀀스, MySQL인 경우 auto_increment
	
	@Column(nullable = false, length = 30, unique = true) // 널 값이 올 수 없고 최대 길이가 30!
	private String username; // id 
	
	@Column(nullable = false, length = 100) // PW	가 123456 6자리 일 경 -> 해쉬로 변경해서 비밀번호를 암호할 것 이기 떄문에 글자수 100을 주었다.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") //기본 권한은 user라고 설정해주는 어노테이션
	@Enumerated(EnumType.STRING)
	private RoleType role; //DB는 RoleType 이라는게 없다. -> Type이 RoleType class에 적혀있는 녀석으로 고정된다.
	// Enum	을 쓰는게 좋다. -> 어떤 이름의 도메인을 만들어 줄 수 있다.
	//ADMIN, USER, MANAGER 권한을 줄 수 있다.
	//도메인은 범위를 뜻한다. ex) 성별의 도메인 -> 남/여, 학생의 도메인 -> 대학생/고등학생/중학생/초등
	
	@CreationTimestamp // 시간이 자동으로 입력되게 하는 어노테이션
	private Timestamp createDate;
}
