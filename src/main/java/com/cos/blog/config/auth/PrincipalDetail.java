package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 한다.
public class PrincipalDetail implements UserDetails { // 스프링 시큐리티의 세션에 UserDetails 타입의 pricipalDetail이 저장된다.

	private User user; // 객체를 품고있음(콤포지션)

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 안잠겨 있는지 나타낸다. (true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되었느지 여부를 리턴한다. (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인지 리턴한다. (true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정의 권한을 나타낸다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();

		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { // ROLE prefix를 걸어줘야 한다. return
		 * "ROLE_"+user.getRole(); // 스프링에서 권한을 받을 때 앞에 꼭 ROLE_을 붙여줘야 한다. // ROLE_USER
		 * 리턴된다. } });
		 */ // 이렇게 쓴 이유는 자바는 파라미터로 메서드를 넣지 못함 그래서 억지로 오브젝트를 만들어서 넣은 것인
		// 람다식으로 사용하면 아래와 같이 아주 간단하게 줄여진다.
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});

		return collectors;
	}

}
