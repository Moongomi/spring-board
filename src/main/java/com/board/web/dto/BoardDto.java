package com.board.web.dto;

import com.board.domain.board.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;

    public BoardDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();

    }
}
