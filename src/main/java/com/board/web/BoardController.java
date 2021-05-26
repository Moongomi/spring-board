package com.board.web;

import com.board.domain.board.Board;
import com.board.service.board.BoardService;

import com.board.web.dto.BoardDto;
import com.board.web.dto.BoardSaveDto;
import com.board.web.dto.BoardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String post(Model model,Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        return "board/post.html";
    }

    @PostMapping("/board/post")
    public String save(BoardSaveDto boardSaveDto){
        boardService.savePost(boardSaveDto);
        return "redirect:/board/list";
    }

    @GetMapping("/board/post/{id}")
    public String findById (@PathVariable Long id,Model model){
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("post",boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PutMapping("/post/edit/{id}")
    public String update(@PathVariable Long id, BoardUpdateDto requestDto){
        boardService.update(id,requestDto);
        return "redirect:/board/list";
    }

}