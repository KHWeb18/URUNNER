package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.PurchasedLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasedLectureRepository extends JpaRepository<PurchasedLecture,Long> {

    public PurchasedLecture findByTitle(String title);
    public List<PurchasedLecture> findByMemberNo(Long memberNo);
}
