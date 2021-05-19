package com.board.web;

import com.board.service.board.BoardService;

import com.board.web.dro.BoardSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String list(Model model){
        model.addAttribute("postList",boardService.findAllDesc());
        return "board/list.html";
    }

    @GetMapping("/board/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/board/post")
    public String save(BoardSaveDto boardSaveDto){
        boardService.savePost(boardSaveDto);
        return "redirect:/board/list";
    }
}