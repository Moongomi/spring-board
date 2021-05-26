package com.board.service.board;

import com.board.domain.board.Board;
import com.board.web.dto.BoardDto;
import com.board.web.dto.BoardListDto;
import com.board.web.dto.BoardSaveDto;
import com.board.domain.board.BoardRepository;
import com.board.web.dto.BoardUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Long update(Long id, BoardUpdateDto boardUpdateDto){
        Board board = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        board.update(boardUpdateDto.getTitle(), boardUpdateDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public List<BoardListDto> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }

    public BoardDto findById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new BoardDto(entity);
    }
}
