package com.urunner.khweb.entity.sort;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urunner.khweb.entity.lecture.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<CategoryLecture> lectureList = new ArrayList<>();

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
