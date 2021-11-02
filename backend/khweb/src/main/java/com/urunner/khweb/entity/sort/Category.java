package com.urunner.khweb.entity.sort;


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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CategoryLecture> lectureList = new ArrayList<>();

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
