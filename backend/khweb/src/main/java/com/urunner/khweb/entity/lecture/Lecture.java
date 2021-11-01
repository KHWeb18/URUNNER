package com.urunner.khweb.entity.lecture;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor @Getter
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecture_id;

    private String writer;

    private String title;

    private String description;

    private String thumb_path;

    private String detail_path;

    private String category;

    private Long price;

    private boolean inProgress;

    private boolean discounted;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureList> lectureLists = new ArrayList<>();

}
