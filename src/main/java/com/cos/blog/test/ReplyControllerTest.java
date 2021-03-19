package com.cos.blog.test;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// 무한 참조 테스트
@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    // Board, Reply, User 모델들이 서로의 id를 계속해서 참조해서 무한 참조가 일어난다.
    // User는 연관관계가 없기 때문에 깔끔하게 끝난다
    // Reply에서 무한참조 문제가 발생
    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id){
        // getReplys를 리턴할 때 문제가 생긴다. Reply안에는 Board를 다시 리턴하기 때문에 다시 getReplys를 리턴한다. 이것이 무한 반복
        // board를 셀렉트하게되면 user의 정보도 뽑아준다(EAGER 전략이라서),
        // 또 reply의 정보도 뽑아주는데(EAGER 전략) board에 대한 정보는 reply보드에 없다.
        // 그 이유는 JsonIgnoreProperties 어노테이션을 사용해서 그렇다.
        return boardRepository.findById(id).get(); // jackson 라이브러리(object를 json으로 리턴) => 모델의 getter를 호출
   }

   @GetMapping("/test/reply")
    public List<Reply> getReply(){
        return replyRepository.findAll();
   }
}
