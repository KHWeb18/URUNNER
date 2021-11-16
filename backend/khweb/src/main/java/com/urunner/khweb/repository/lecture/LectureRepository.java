package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    public List<Lecture> findByWriter(String writer);

//    @Query("select l from Lecture l join fetch l.lectureLists where l.lecture_id =: id")
//    public Optional<Lecture> letureAll(Long id);

    @Query(value = "select l from Lecture l join fetch l.categoryList c join fetch c.category cl where l.inProgress = :inProgress",
            countQuery = "select count(l) from Lecture l")
    Page<Lecture> findByInProgressTrue(@Param("inProgress")boolean inProgress, Pageable pageable);

}
