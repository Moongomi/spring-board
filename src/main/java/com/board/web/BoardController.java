package com.board.web;

import com.board.domain.board.Board;
import com.board.service.board.BoardService;

import com.board.web.dto.BoardDto;
import com.board.web.dto.BoardListDto;
import com.board.web.dto.BoardSaveDto;
import com.board.web.dto.BoardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String list(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<BoardListDto> boardList = boardService.getBoardList(pageable);
        Integer[] pageList = boardService.getPageList(pageable);
        model.addAttribute("postList", boardList);
        model.addAttribute("pageList", pageList);
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

    @GetMapping("/board/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PutMapping("/board/edit/{id}")
    public String update(@PathVariable Long id, BoardUpdateDto requestDto){
        boardService.update(id,requestDto);
        return "redirect:/board/list";
    }
    @DeleteMapping("/board/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }


    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("postList", boardService.searchPosts(keyword,pageable));
        return "board/list.html";
    }

}