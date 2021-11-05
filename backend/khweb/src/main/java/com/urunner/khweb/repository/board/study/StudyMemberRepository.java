package com.urunner.khweb.repository.board.study;

import com.urunner.khweb.entity.board.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {
    @Query(value = "select * from study_member where board_no = :boardNo", nativeQuery = true)
    List<StudyMember> selectStudyBoardNo(Long boardNo);

//    @Query(value = "insert into study_member email, name, board_no, introduce value :email, :name, :boardNo, :introduce", nativeQuery = true)
//    void applyMember(String email, String name, Long boardNo, String introduce);

//    @Query(value = "insert into study_member email, name, board_no, introduce value :email, :name, :boardNo, :introduce", nativeQuery = true)
//    void applyMember(String email, String name, Long boardNo, String introduce);

    @Query(value = "select * from study_member where email = :email and board_no = :boardNo", nativeQuery = true)
    Optional<StudyMember> checkMember(String email, Long boardNo);

    @Query(value = "SELECT COUNT(IF(board_no = :boardNo, board_no, NULL)) FROM study_member;", nativeQuery = true)
    Long checkLength(Long boardNo);


}