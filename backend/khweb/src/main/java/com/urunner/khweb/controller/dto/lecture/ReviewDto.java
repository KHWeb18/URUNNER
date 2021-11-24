package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;

@Data
public class ReviewDto {

    private Long lectureId;
    private Long rating;
    private String content;
    private String writer;

    public ReviewDto(Long lectureId, Long rating, String content, String writer) {
        this.lectureId = lectureId;
        this.rating = rating;
        this.content = content;
        this.writer = writer;
    }
}
