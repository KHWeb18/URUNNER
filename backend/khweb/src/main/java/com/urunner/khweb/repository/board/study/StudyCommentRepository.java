package com.urunner.khweb.repository.board.study;

import com.urunner.khweb.entity.board.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

    @Query(value = "select * from studycomment where board_no = :boardNo order by 4, 1, 5", nativeQuery = true)
    List<StudyComment> selectStudyComment(Long boardNo);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long extractionCommentNo();

    @Transactional
    @Modifying
    @Query(value="update StudyComment u set u.groupNo = :#{#comment.commentNo} WHERE u.commentNo = :#{#comment.commentNo}")
    void changeGroupNo(StudyComment comment);

}