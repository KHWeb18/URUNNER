package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class LectureListDto {

    public Long lectureList_id;

    public String topic;

    public String section;

    public LectureListDto(Long lectureList_id, String topic, String section) {
        this.lectureList_id = lectureList_id;
        this.topic = topic;
        this.section = section;
    }
}
