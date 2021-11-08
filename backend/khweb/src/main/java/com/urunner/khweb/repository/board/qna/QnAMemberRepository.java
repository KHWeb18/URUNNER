package com.urunner.khweb.repository.board.qna;

import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.entity.board.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QnAMemberRepository extends JpaRepository<QnAMember, Long> {
    @Query(value = "select * from qna_member where board_no = :boardNo", nativeQuery = true)
    List<QnAMember> selectStudyBoardNo(Long boardNo);

    @Query(value = "select * from qna_member where email = :email and board_no = :boardNo", nativeQuery = true)
    Optional<QnAMember> checkMember(String email, Long boardNo);

    @Query(value = "SELECT COUNT(IF(board_no = :boardNo, board_no, NULL)) FROM qna_member;", nativeQuery = true)
    Long checkLength(Long boardNo);


}