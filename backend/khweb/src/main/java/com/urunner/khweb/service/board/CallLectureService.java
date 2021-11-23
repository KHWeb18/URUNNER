package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.lecture.Lecture;

import java.util.List;

public interface CallLectureService {
    List<Object[]> selectLectureList() throws Exception;
    List<Object[]> selectLectureListWithTag(Long categoryId) throws Exception;
    List<Object[]> selectLectureListWithWord(String word) throws Exception;
    List<Object[]> selectMyLectureList(Long memberNO) throws Exception;
}