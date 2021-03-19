package com.cos.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 데이터를 아래와 같은 형태로 전달시킨다.
// 계층 간의 데이터 통신을 위해 사용한다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;
}
