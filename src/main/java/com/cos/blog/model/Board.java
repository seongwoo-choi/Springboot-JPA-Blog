package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(다른언어 포함) Object -> 테이블로 맵핑해주는 기술!!! 변수명을 바꾸고 저장하면 DB도 바로 수정이 된다!!!
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
@Entity 
public class Board {
	
	@Id //Primary Key 가 된다는 것을 알려주는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // Oracle인 경우 시퀀스, MySQL인 경우 auto_increment
	
	@Column(nullable = false, length = 100) //널 값이 올 수 없고 최대 길이가 30!
	private String title; // 게시판 이름
	
	@Lob //대용량 데이터 
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	private int count; //조회수 
	
	//Board를 셀렉트 할 때 userid 를 가져와라는 기본전략.
	//EAGER --> 모든 정보를 다 가져올 때 쓰는 전략, LAZY --> 페이지에서 데이터들이 다 나타나지 않을 때 쓰는 전략
	@ManyToOne(fetch = FetchType.EAGER)//Many = Board, User = One --> 한명의 유저에 의해 여러개의 게시물이 써질 수 있다.
	@JoinColumn(name="userId") //FK를 설정해 준다.
	private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	//JoinColumn을 해주지 않아도 된다. why? 원자성을 위배하기 때문이다.
	//하나의 게시물은 여러개의 답글을 가질  수 있다.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다.(난 FK가 아니에요.) DB에 칼럼을 만들지 마세요.  
	private List<Reply> reply; //즉 FK는 Reply테이블의 boardId가 FK라는 것을 나타낸다.
	//나는 그냥 Board를 셀렉트할 때 Join문을 통해서 값을 얻기 위해 필요한 거에요.
	
	
	@CreationTimestamp // 시간이 자동으로 입력되게 하는 어노테이션
	private Timestamp createDate;
}
