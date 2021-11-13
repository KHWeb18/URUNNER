package com.urunner.khweb.repository.board.study;

import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.Study;
import com.urunner.khweb.entity.board.StudyComment;
import com.urunner.khweb.entity.board.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudyBoardRepository extends JpaRepository<Study, Long> {
    Optional<Study> findByBoardNo(Long boardNo);
//    List<Study> findByComplete(String complete);

    @Query(value = "select * from studyboard where notice = 'true' union select * from studyboard where complete = :complete order by 8, 1", nativeQuery = true)
    List<Study> findByComplete(String complete);

    @Query(value = "select * from studyboard order by 8, 1", nativeQuery = true)
    List<Study> selectStudyList();

    @Transactional
    @Modifying
    @Query("update Study u set u.title = ?1, u.content = ?2, u.complete = ?4, u.currentNum = ?5, u.tags = ?6, u.fit = ?7, u.notice = ?8 where u.boardNo = ?3")
    void updatePost(String title, String content, Long boardNo, String complete, Long currentNum, String tags, Long fit, String notice);

    @Transactional
    @Modifying
    @Query("update Study u set u.currentNum = ?1 where u.boardNo = ?2")
    void updateCurrentNum(Long currentNum, Long boardNo);

    @Transactional
    @Modifying

    @Query("update Study u set u.views = ?1 where u.boardNo = ?2")
    void updateViews(Long views, Long boardNo);

    @Transactional
    @Modifying
    @Query("update Study u set u.comments = u.comments + ?2 where u.boardNo = ?1")
    void updateComments(Long boardNo, Long upDown);

}
