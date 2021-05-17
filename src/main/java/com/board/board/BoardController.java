package com.board.board;

import com.board.board.BoardDto;
import com.board.board.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String list() {
        return "board/list.html";
    }

    @GetMapping("/board/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/board/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}