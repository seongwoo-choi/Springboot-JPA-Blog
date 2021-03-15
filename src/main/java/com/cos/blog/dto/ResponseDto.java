package com.cos.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
	// DTO는 계층간 데이터 이동을 위해서 사용하는 객체이다.
	// 논리 로직이 아예 없고 게터와 세터만 존재합니다.
	// 이 DTO 객체가 Client, Controller, Service, Repository 사이를 오고 간다. 
	int status;
	T data;
}
