//package com.urunner.khweb.entity.lecture;
//
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity @NoArgsConstructor @Getter
//public class LectureImage {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long lectureImage_id;
//
//    private String thumb_path;
//
//    private String detail_path;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "lecture_id")
//    private Lecture lecture;
//
//    @Builder
//    public LectureImage(String thumb_path, String detail_path, Lecture lecture) {
//        this.thumb_path = thumb_path;
//        this.detail_path = detail_path;
//        this.lecture = lecture;
//    }
//
//    public void setLecture(Lecture lecture) {
//        this.lecture = lecture;
//        lecture.getLecture_images().add(this);
//    }
//}
