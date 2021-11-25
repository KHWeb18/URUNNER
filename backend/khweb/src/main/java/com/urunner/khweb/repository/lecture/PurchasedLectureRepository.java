package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.PurchasedLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchasedLectureRepository extends JpaRepository<PurchasedLecture,Long> {

    public PurchasedLecture findByTitle(String title);
    public List<PurchasedLecture> findByMemberNo(Long memberNo);

    @Query("select count(*) from PurchasedLecture p where p.lecture_id = :id")
    public Optional<Long> getPurchasedLectureCount(@Param("id")Long id);
}
