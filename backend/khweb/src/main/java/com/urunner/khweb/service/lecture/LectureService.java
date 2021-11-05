package com.urunner.khweb.service.lecture;


import com.urunner.khweb.controller.dto.lecture.LectureDto;
import com.urunner.khweb.controller.dto.lecture.LectureListDto;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureList;
import com.urunner.khweb.entity.lecture.LectureVideo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LectureService {

//    public void lectureVideo(LectureVideo lectureVideo, EnrollLectureVideoDto enrollLectureVideoDto);

    public void lectureEnroll(Lecture lecture);

    public void lectureAddImage(String thum, String image, Long id);

    public void lectureRegister(String writer, String title, Long price, String desc, String category);

    public void saveLectureSection(Long lectureId, String topic);

    public Page<LectureListDto> findAllLectureSection(Long lectureId);

    public void videoUpload(String title, String desc, String duration, Long id, String path);

    public List<LectureDto> getLectureList(String writer);

}
