package com.board.service.board;

import com.board.domain.board.Board;
import com.board.web.dto.BoardDto;
import com.board.web.dto.BoardListDto;
import com.board.web.dto.BoardSaveDto;
import com.board.domain.board.BoardRepository;
import com.board.web.dto.BoardUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;   //블럭에 존재하는 페이지 번호 수

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

    @Transactional
    public List<BoardListDto> getBoardList(Pageable pageable){
        return boardRepository.findAll(pageable).stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }

    public Integer[] getPageList(Pageable pageable){

        Page<Board> boardList = boardRepository.findAll(pageable);
        int pageNum = boardList.getPageable().getPageNumber();
        int totalPage = boardList.getTotalPages();
        Integer[] pageList = new Integer[totalPage];
        int startPage = ((pageNum)/BLOCK_PAGE_NUM_COUNT)*BLOCK_PAGE_NUM_COUNT+1;
        int endPage = startPage+BLOCK_PAGE_NUM_COUNT-1;
        endPage = totalPage<endPage?totalPage:endPage;

        for(int i = 0; i < endPage;i++){
            pageList[i] = i;
        }

        return pageList;
    }

    @Transactional
    public List<BoardListDto> searchPosts(String keyword,Pageable pageable) {
        return boardRepository.findAllSearch(keyword,pageable).stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        boardRepository.delete(board);
    }

    public BoardDto findById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new BoardDto(entity);
    }
}
