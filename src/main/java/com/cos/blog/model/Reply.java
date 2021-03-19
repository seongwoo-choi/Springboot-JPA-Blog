package com.cos.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.blog.dto.ReplySaveRequestDto;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // Reply 객체와 Table을 맵핑해주는 어노테이션
public class Reply {
	
	// JPA가 객체를 관리할 때 식별할 기본키를 지정한다.
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment

	// 필드와 컬럼을 맵핑해주는 어노테이션 
	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne //여러개의 답변을 한명의 유저가 적을 수 있다.
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne //연관관계 설정 : 여러개의 답변은 하나의 게시글에 존재할 수 있다.
	@JoinColumn(name="boardId") //DB테이블 칼럼에 boardId라는 녀석이 생성되도록 하는 어노테이션 
	private Board board;
	
	@CreationTimestamp
	private LocalDateTime createDate;

}








