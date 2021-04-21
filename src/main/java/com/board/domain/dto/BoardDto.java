package com.board.domain.dto;
/*
DTO는 Controller와 Service 사이에서 데이터를 주고받습니다.
 */

import com.board.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
//@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity(){

        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();

        return build;
    }

    @Builder
    public BoardDto(Long id,String author,String title,String content,LocalDateTime createdDate,LocalDateTime modifiedDate){
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }




}
