package com.board.domain.board;

import com.board.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    List<Board> findAllDesc();

    @Query(value = "SELECT b FROM Board b WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword% OR b.author LIKE %:keyword%",
            countQuery = "SELECT COUNT(b.id) FROM Board b WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword% OR b.author LIKE %:keyword%"
    )
    List<Board> findAllSearch(String keyword);
}
