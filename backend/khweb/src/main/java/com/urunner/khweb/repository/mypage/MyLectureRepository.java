//package com.urunner.khweb.repository.mypage;
//
//import com.urunner.khweb.entity.board.QnA;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface MyLectureRepository extends JpaRepository<QnA, Long> {
//    @Query(value = "select * from qnaboard where notice = 'true' union select * from qnaboard where complete = :complete order by 7, 1", nativeQuery = true)
//    List<QnA> findByComplete(String complete);
//}