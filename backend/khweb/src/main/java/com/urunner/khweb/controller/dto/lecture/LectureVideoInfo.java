package com.urunner.khweb.controller.dto.lecture;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LectureVideoInfo {

    private String writer;
    private String path;

    public LectureVideoInfo(String writer, String path) {
        this.writer = writer;
        this.path = path;
    }
}
