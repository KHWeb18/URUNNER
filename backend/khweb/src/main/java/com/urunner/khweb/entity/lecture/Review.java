package com.urunner.khweb.entity.lecture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor @Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rating;

    private String content;

    private String writer;

    @Builder
    public Review(Long rating, String content, String writer) {
        this.rating = rating;
        this.content = content;
        this.writer = writer;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    Lecture lecture;

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
