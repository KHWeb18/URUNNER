package com.urunner.khweb.entity.lecture;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor @Getter
public class LectureVideo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String sequence;

    private String duration;

    private String videoPath;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectureList_id")
    private LectureList lectureList;

    @Builder
    public LectureVideo(String title, String description, String sequence, String duration, String videoPath) {
        this.title = title;
        this.description = description;
        this.sequence = sequence;
        this.duration = duration;
        this.videoPath = videoPath;
    }

    public void setLectureList(LectureList lectureList) {
        this.lectureList = lectureList;
        lectureList.getLectureVideos().add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
