package com.urunner.khweb.repository.board.qna;

import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface QnABoardRepository extends JpaRepository<QnA, Long> {
    Optional<QnA> findByBoardNo(Long boardNo);
    List<QnA> findByComplete(String complete);


    @Transactional
    @Modifying
    @Query("update QnA u set u.title = ?1, u.content = ?2, u.complete = ?4, u.currentNum = ?5, u.tags = ?6 where u.boardNo = ?3")
    void updatePost(String title, String content, Long boardNo, String complete, Long currentNum, String tags);

    @Transactional
    @Modifying
    @Query("update QnA u set u.currentNum = ?1 where u.boardNo = ?2")
    void updateCurrentNum(Long currentNum, Long boardNo);

    @Transactional
    @Modifying

    @Query("update QnA u set u.views = ?1 where u.boardNo = ?2")
    void updateViews(Long views, Long boardNo);

    @Transactional
    @Modifying
    @Query("update QnA u set u.comments = u.comments + ?2 where u.boardNo = ?1")
    void updateComments(Long boardNo, Long upDown);
}