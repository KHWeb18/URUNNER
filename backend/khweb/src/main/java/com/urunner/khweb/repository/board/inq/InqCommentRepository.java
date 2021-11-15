package com.urunner.khweb.repository.board.inq;

import com.urunner.khweb.entity.board.InqComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InqCommentRepository extends JpaRepository<InqComment, Long> {

    @Query(value = "select * from inqcomment where board_no = :boardNo order by 4, 1, 5", nativeQuery = true)
    List<InqComment> selectInqComment(Long boardNo);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long extractionCommentNo();

    @Transactional
    @Modifying
    @Query(value="update InqComment u set u.groupNo = :#{#comment.commentNo} WHERE u.commentNo = :#{#comment.commentNo}")
    void changeGroupNo(InqComment comment);

}