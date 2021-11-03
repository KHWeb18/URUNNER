package com.urunner.khweb.repository.lecture;


import com.urunner.khweb.entity.lecture.LectureList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureListRepository extends JpaRepository<LectureList, Long> {

    @Query("select l from LectureList l where writer = :writer and topic = :topic")
    public LectureList enrollLectureList(String writer, String topic);
}
