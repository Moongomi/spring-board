package com.board.service.board;

import com.board.domain.board.Board;
import com.board.web.dro.BoardListDto;
import com.board.web.dro.BoardSaveDto;
import com.board.domain.board.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardSaveDto boardSaveDto){
        return boardRepository.save(boardSaveDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<BoardListDto> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }
}
