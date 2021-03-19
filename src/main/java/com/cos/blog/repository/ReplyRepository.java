package com.cos.blog.repository;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    // 인터페이스에서는 public 생략 가능
    // native 쿼리 사용 => 영속화할 필요가 없다.
    @Modifying
    @Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해 준다. 1이 리턴되면 1개가 save 됐다는 것. -1은 오류, 0은 세이브된 것이 없음.

}
