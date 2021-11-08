package com.urunner.khweb.repository.lecture;


import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureList;
import com.urunner.khweb.entity.lecture.LectureVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureVideoRepository extends JpaRepository<LectureVideo, Long> {
    public List<LectureVideo> findByLectureList(LectureList lectureList);

}
