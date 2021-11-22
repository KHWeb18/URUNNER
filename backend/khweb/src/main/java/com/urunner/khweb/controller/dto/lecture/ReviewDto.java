package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;

@Data
public class ReviewDto {

    private Long lectureId;
    private Long rating;
    private String content;
}
