package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 아래 3가지 어노테이션은 한개의 세트 다 써줘야 한다.
@Configuration // 설정 파일을 빈 등록하기 위한 어노테이션 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터 등록 = 활성화 된 스프링 시큐리티의 설정을 여기서 하겠다. 
//Controller에서 특정 권한이 있는 유저만 접근을 허용하려면 @PreAuthorize 어노테이션을 사용하는데, 해당 어노테이션을 활성화 시키는 어노테이션이다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 했을 때 권한 및 인증을 미리 체크하겠다. 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
<<<<<<< HEAD
		
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean // IoC가 되요!!
=======

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}

	@Bean // IoC 가 된다. 뭐가?? 
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
	public BCryptPasswordEncoder encodePWD() {
		// return new BCryptPasswordEncoder(); 이 값이 bean으로 등록된다.
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인을 해줄 때 password를 가로채기를 하는데
	// 해당 password가 어떤 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// passwordEncoder(encodePWD()) -> 해쉬화가 된 패스워드 값을 userDetailsService(null) 이 null 자리(실제 로그인을 하는 객체)에 있는 객체와 비교를 한다.
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()  // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋다), ajax 통신 시에 토큰이 없기 때문에 통신을 막아버리기 때문이다.
			.authorizeRequests() // request 가 들어올 때
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**") // /, auth, js, css, image 이하의 모든 경로가 오면 
				.permitAll() // 누구나 들어올 수 있다.
				.anyRequest() // /, auth, js, css, image 이하의 모든 경로를 제외한 요청은
				.authenticated() // 인증이 되어야 한다. /, auth, js, css, image를 갈 때 빼고는 모두 인증이 필요로 하다.
			.and()
				.formLogin() // 인증이 필요한 곳으로 요청이 오면 
				.loginPage("/auth/loginForm") // 로그인 페이지로 이동(로그인을 시키는 경로로 이동)을 시킨다.
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인을 해준다.
				.defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
	}
}
