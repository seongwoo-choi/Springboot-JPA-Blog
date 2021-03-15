package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter //게터만들
//@Setter //세터만들
//@Data //게터, 세터 만들기
//@AllArgsConstructor //모든 변수 생성자 만들기
//@RequiredArgsConstructor //final이 붙은 변수들에 대한 생성자를 만들어준다.
@Data //게터, 세터 만들기
@NoArgsConstructor
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
