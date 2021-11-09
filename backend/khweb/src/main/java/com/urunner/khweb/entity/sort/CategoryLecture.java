package com.urunner.khweb.entity.sort;


import com.urunner.khweb.entity.lecture.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor
public class CategoryLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Lecture_id")
    private Lecture lecture;


    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

}
