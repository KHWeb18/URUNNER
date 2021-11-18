package com.urunner.khweb.repository.board;

import com.urunner.khweb.entity.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallLectureRepository extends JpaRepository<Lecture, Long> {

    @Query(value = "select lecture.* from category_lecture join lecture ON lecture.lecture_id = category_lecture.lecture_id where category_lecture.category_id = :categoryId", nativeQuery = true)
    List<Lecture> selectLectureListWithTag(Long categoryId);

    @Query(value = "select * from lecture", nativeQuery = true)
    List<Lecture> selectLectureList();
}