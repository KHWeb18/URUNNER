package com.urunner.khweb.repository.board.qna;

import com.urunner.khweb.entity.board.QnAComment;
import com.urunner.khweb.entity.board.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QnACommentRepository extends JpaRepository<QnAComment, Long> {

    @Query(value = "select * from qnacomment where board_no = :boardNo order by 4, 1, 5", nativeQuery = true)
    List<QnAComment> selectStudyComment(Long boardNo);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long extractCommentNo();

    @Transactional
    @Modifying
    @Query(value="update QnAComment u set u.groupNo = :#{#comment.commentNo} WHERE u.commentNo = :#{#comment.commentNo}")
    void changeGroupNo(QnAComment comment);

}