package com.cos.blog.service;

import com.cos.blog.model.Reply;
import com.cos.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
@Service
public class BoardService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 이 글쓰기 서비스 전체가 하나의 트랜잭션이 된다.
	// 글쓰기 할 때 유저 정보가 필요하다.
	@Transactional
	public void 글쓰기(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	// select만 하는 작업이기 때문에 트랜잭션 어노테이션을 안써도 되지만
	// 쓰는 편이 좋기 때문에 select만 하는 작업이라는 것을 알려주기 위해 readOnly = true를 붙여주었다.
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	// select만 하는 작업이기 때문에 트랜잭션 어노테이션을 안써도 되지만
	// 쓰는 편이 좋기 때문에 select만 하는 작업이라는 것을 알려주기 위해 readOnly = true를 붙여주었다.
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		System.out.println("글삭제하기 : "+id);
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply){

		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 아이디를 찾을 수 없습니다.");
		});

		requestReply.setUser(user);
		requestReply.setBoard(board);

		replyRepository.save(requestReply);
	}

	
	// boardService의 전체적인 흐름도
	// BoardController에서 board/saveForm 요청을 하면 board/saveForm jsp 파일이 열린다.
	// board/saveForm에서 타이틀이랑 컨텐트를 입력을 하고 제출 버튼을 누르면 
	// 이 두개의 데이터 정보를 자바스크립트로 보내고
	// 자바스크립트에서 아약스 통신을 통해서 api/board 로 json 타입으로 변환한 데이터를 보내준다.
	// @RequestBody Board board 에는 타이틀과 콘텐트 값만을 가지고 있다.
	// 이 상태로 글쓰기 서비스를 실행하면 안되기 때문에 principal을 통해 세션에 접근해서 User 객체를 가져온다.
	// 그 상태로 글쓰기 서비스를 시작하게 되면 글쓰기 내부 로직을 처리를 하고 boardRepository를 통해서 board 객체를 DB에 insert 하게 된다.
	// 저장이 끝나면 다시 BoardApiController로 돌아와서 return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 을 리턴해준다.
	// 위의 응답이 오면 board.js 에서 .done(function(resp){alert("글쓰기 완료되었습니다."); loaction.hreg="/";}} 함수가 실행되고 끝이 난다.
	
		
}
