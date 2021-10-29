package com.urunner.khweb.repository.board;

import com.urunner.khweb.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeCommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select comment_no, board_no, content, writer, name, reg_date from freecomment where board_no = :boardNo", nativeQuery = true)
    List selectFreeComment(Long boardNo);

}