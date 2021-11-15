package com.urunner.khweb.repository.board.inq;

import com.urunner.khweb.entity.board.Inq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface InqBoardRepository extends JpaRepository<Inq, Long> {
    Optional<Inq> findByBoardNo(Long boardNo);

    @Query(value = "select * from inqboard where notice = 'true' union select * from inqboard where writer = :userId order by 8, 1", nativeQuery = true)
    List<Inq> findByUserId(String userId);

    @Query(value = "select * from inqboard where notice = 'true' union select * from inqboard where complete = :complete order by 8, 1 desc", nativeQuery = true)
    List<Inq> findByComplete(String complete);

    @Query(value = "select * from inqboard order by 8, 1", nativeQuery = true)
    List<Inq> selectInqList();

    @Transactional
    @Modifying
    @Query("update Inq u set u.title = ?1, u.content = ?2, u.complete = ?4, u.currentNum = ?5, u.tags = ?6, u.notice = ?7 where u.boardNo = ?3")
    void updatePost(String title, String content, Long boardNo, String complete, Long currentNum, String tags, String notice);

    @Transactional
    @Modifying
    @Query("update Inq u set u.currentNum = ?1 where u.boardNo = ?2")
    void updateCurrentNum(Long currentNum, Long boardNo);

    @Transactional
    @Modifying
    @Query("update Inq u set u.views = ?1 where u.boardNo = ?2")
    void updateViews(Long views, Long boardNo);

    @Transactional
    @Modifying
    @Query("update Inq u set u.comments = u.comments + ?2 where u.boardNo = ?1")
    void updateComments(Long boardNo, Long upDown);
}