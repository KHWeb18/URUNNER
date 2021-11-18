package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.lecture.Lecture;

import java.util.List;

public interface CallLectureService {
    List<Lecture> selectLectureList() throws Exception;
    List<Lecture> selectLectureListWithTag(Long categoryId) throws Exception;
}