package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;

@Data
public class LectureDto {

    private Long id;
    private String writer;
    private String title;
    private String desc;
    private Long price;
    private boolean inProgress;
    private boolean discounted;
    private String thumbPath;

    public LectureDto(Long id, String writer, String title, String desc, Long price, boolean inProgress, boolean discounted, String thumbPath) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.inProgress = inProgress;
        this.discounted = discounted;
        this.thumbPath = thumbPath;
    }
}
