package com.urunner.khweb.repository.board.free;

import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.QnAComment;
import com.urunner.khweb.entity.board.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FreeBoardRepository extends JpaRepository<Free, Long> {
    Optional<Free> findByBoardNo(Long boardNo);

    @Query(value = "select * from freeboard where notice = 'true' union select * from freeboard where complete = :complete order by 7, 1", nativeQuery = true)
    List<Free> findByComplete(String complete);

    @Query(value = "select * from freeboard order by 7, 1", nativeQuery = true)
    List<Free> selectFreeList();

    @Transactional
    @Modifying
    @Query("update Free u set u.title = ?1, u.content = ?2, u.complete = ?4, u.currentNum = ?5, u.tags = ?6, u.notice = ?7 where u.boardNo = ?3")
    void updatePost(String title, String content, Long boardNo, String complete, Long currentNum, String tags, String notice);

    @Transactional
    @Modifying
    @Query("update Free u set u.currentNum = ?1 where u.boardNo = ?2")
    void updateCurrentNum(Long currentNum, Long boardNo);

    @Transactional
    @Modifying
    @Query("update Free u set u.views = ?1 where u.boardNo = ?2")
    void updateViews(Long views, Long boardNo);

    @Transactional
    @Modifying
    @Query("update Free u set u.comments = u.comments + ?2 where u.boardNo = ?1")
    void updateComments(Long boardNo, Long upDown);
}