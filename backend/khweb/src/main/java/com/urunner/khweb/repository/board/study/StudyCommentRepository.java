package com.urunner.khweb.repository.board.study;

import com.urunner.khweb.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCommentRepository extends JpaRepository<Comment, Long> {

    //데이터 설계 다시하자 대댓글 가자 걍걍

   @Query(value = "select comment_no, board_no, content, writer, name, reg_date from freecomment where board_no = :boardNo", nativeQuery = true)
    List<Comment> selectFreeComment(Long boardNo);
}