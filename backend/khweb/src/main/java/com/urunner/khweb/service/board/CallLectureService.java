package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureVideo;

import java.util.List;
import java.util.Optional;

public interface CallLectureService {
    List<Object[]> selectLectureList() throws Exception;
    List<Object[]> selectLectureListWithTag(Long categoryId) throws Exception;
    List<Object[]> selectLectureListWithWord(String word) throws Exception;
    List<Object[]> selectMyLectureList(Long memberNO) throws Exception;
    Optional<Lecture> callLatestLecture(Long videoId) throws Exception;

}