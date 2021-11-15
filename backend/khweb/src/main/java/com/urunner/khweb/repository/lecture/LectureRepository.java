package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    public List<Lecture> findByWriter(String writer);

//    @Query("select l from Lecture l join fetch l.lectureLists where l.lecture_id =: id")
//    public Optional<Lecture> letureAll(Long id);

}
