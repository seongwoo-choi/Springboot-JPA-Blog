package com.cos.blog.config;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 
// 아래 3가지 어노테이션은 한개의 세트 다 써줘야 한다.
@Configuration // 설정 파일을 빈 등록하기 위한 어노테이션 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터 등록 = 활성화 된 스프링 시큐리티의 설정을 여기서 하겠다. 
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 했을 때 권한 및 인증을 미리 체크하겠다. 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() // request 가 들어올 때
				.antMatchers("/auth/**") // auth 이하의 모든 경로가 오면
				.permitAll() // 누구나 들어올 수 있다.
				.anyRequest() // auth 이하의 모든 경로를 제외한 요청은  
				.authenticated() // 인증이 되어야 한다. auth를 갈 때 빼고는 모두 인증이 필요로 하다.
			.and()
				.formLogin() // 인증이 필요한 곳으로 요청이 오면 
				.loginPage("/auth/loginForm"); // 로그인 페이지로 이동(로그인을 시키는 경로로 이동)을 시킨다.
	}
}
