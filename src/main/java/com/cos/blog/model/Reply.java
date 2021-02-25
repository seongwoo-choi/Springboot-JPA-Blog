package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	
	@Id //Primary Key 가 된다는 것을 알려주는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // Oracle인 경우 시퀀스, MySQL인 경우 auto_increment

	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne //연관관계 설정 : 여러개의 답변은 하나의 게시글에 존재할 수 있다.
	@JoinColumn(name="boardId") //DB테이블 칼럼에 boardId라는 녀석이 생성되도록 하는 어노테이션 
	private Board board;
	
	@ManyToOne //여러개의 답변을 한명의 유저가 적을 수 있다.
	@JoinColumn(name ="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
