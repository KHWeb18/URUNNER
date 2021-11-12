package com.urunner.khweb.controller.dto.lecture;

import com.urunner.khweb.entity.lecture.LectureVideo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LectureListDto2 {

    public Long lectureList_id;

    public String topic;

    public String section;

    public List<LectureVideo> lectureVideoList;

    public LectureListDto2(Long lectureList_id, String topic, String section, List<LectureVideo> lectureVideoList) {
        this.lectureList_id = lectureList_id;
        this.topic = topic;
        this.section = section;
        this.lectureVideoList = lectureVideoList;
    }
}
