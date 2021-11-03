//package com.urunner.khweb.repository.board.study;
//
//import com.urunner.khweb.entity.board.Study;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//public interface StudyBoardRepository extends JpaRepository<Study, Long> {
//    Optional<Study> findByBoardNo(Long boardNo);
//
//    @Transactional
//    @Modifying
//    @Query("update Free u set u.title = ?1, u.content = ?2 where u.boardNo = ?3")
//    void updatePost(String title, String content, Long boardNo);
//}