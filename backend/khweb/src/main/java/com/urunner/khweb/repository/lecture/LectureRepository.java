package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    public List<Lecture> findByWriter(String writer);

}
