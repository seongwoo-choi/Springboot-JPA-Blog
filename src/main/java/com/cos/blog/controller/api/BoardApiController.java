package com.cos.blog.controller.api;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		// @AuthenticationPrincipal PrincipalDetail principal => 컨트롤러에서 세션에 접근할 때 쓰는 방식이다.
		boardService.글쓰기(board, principal.getUser()); // user 오브젝트가 필요함.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		System.out.println("BoardApiController : update : id : "+id);
		System.out.println("BoardApiController : update : board : "+board.getTitle());
		System.out.println("BoardApiController : update : board : "+board.getContent());
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는 게 좋다.
	// dto 사용하지 않는 이유는!!! 작은 프로젝트이기 때문이다..
	// 프로젝트가 커지면 왔다 갔다 하는 데이터 필드가 굉장히 커진다.
	// 그래서 dto를 사용해야 한다. 객체를 왔다 갔다 움직이는 것은 좋은 방법이 아니다.
//	@PostMapping("/api/board/{boardId}/reply")
//	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
//		// @AuthenticationPrincipal PrincipalDetail principal => 컨트롤러에서 세션에 접근할 때 쓰는 방식이다.
//		boardService.댓글쓰기(principal.getUser(), boardId, reply); // user 오브젝트가 필요함.
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}

	// dto를 사용하면 한 번에 여러개의 데이터를 전송할 수 있다.
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		// @AuthenticationPrincipal PrincipalDetail principal => 컨트롤러에서 세션에 접근할 때 쓰는 방식이다.
		boardService.댓글쓰기(replySaveRequestDto); // user 오브젝트가 필요함.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}



