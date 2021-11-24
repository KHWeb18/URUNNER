package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;

@Data
public class LectureReviewDto {

    Long avg;

    Long count;

    public LectureReviewDto(Long avg) {
        this.avg = avg;
    }
}
