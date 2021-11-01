package com.urunner.khweb.entity.lecture;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor @Getter
public class LectureList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureList_id;

    private String section;

    private String topic;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lectureList")
    private List<LectureVideo> lectureVideos = new ArrayList<>();

}
