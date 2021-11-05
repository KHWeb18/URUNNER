package com.urunner.khweb.repository.lecture;


import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LectureListRepository extends JpaRepository<LectureList, Long> {

    @Query("select l from LectureList l where writer = :writer and topic = :topic")
    public LectureList enrollLectureList(String writer, String topic);

    Page<LectureList> findByLecture(Lecture lecture, Pageable pageable);

    public Optional<LectureList> findByTopic(String topic);
}
