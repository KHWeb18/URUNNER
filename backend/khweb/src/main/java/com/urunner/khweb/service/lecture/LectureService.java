package com.urunner.khweb.service.lecture;


import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureVideo;

public interface LectureService {

    public void lectureVideo(LectureVideo lectureVideo, EnrollLectureVideoDto enrollLectureVideoDto);

    public void lectureEnroll(Lecture lecture);

    public void lectureAddImage(String thum, String image, Long id);

    public void lectureRegister(String writer, String title, Long price, String desc, String category);

}
