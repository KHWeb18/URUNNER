package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class LectureVideoDto {

    public Long id;

    public String title;

    public String description;

    public String sequence;

    public String duration;

    public String videoPath;

    public LectureVideoDto(Long id, String title, String description, String sequence, String duration, String videoPath) {
        this.id = id;
        this.title = title;
        this.sequence = sequence;
        this.duration = duration;
        this.videoPath = videoPath;
        this.description = description;
    }
}