package com.urunner.khweb.repository.board.qna;

import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface QnABoardRepository extends JpaRepository<QnA, Long> {
    Optional<QnA> findByBoardNo(Long boardNo);

    @Transactional
    @Modifying
    @Query("update QnA u set u.title = ?1, u.content = ?2, u.complete = ?4, u.currentNum = ?5 where u.boardNo = ?3")
    void updatePost(String title, String content, Long boardNo, String complete, Long currentNum);

    @Transactional
    @Modifying
    @Query("update QnA u set u.currentNum = ?1 where u.boardNo = ?2")
    void updateCurrentNum(Long currentNum, Long boardNo);

    @Transactional
    @Modifying
    @Query("update QnA u set u.views = ?1 where u.boardNo = ?2")
    void updateViews(Long views, Long boardNo);
}