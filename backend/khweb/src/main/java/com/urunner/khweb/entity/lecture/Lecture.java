package com.urunner.khweb.entity.lecture;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urunner.khweb.entity.sort.Category;
import com.urunner.khweb.entity.sort.CategoryLecture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecture_id;

    private String writer;

    private String title;

    private String description;

    private Long price;

    private boolean inProgress;

    private boolean discounted;

    private String thumb_path;

    private String detail_path;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureList> lectureLists = new ArrayList<>();

//    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<LectureImage> lecture_images = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryLecture> categoryList = new ArrayList<>();

    @Builder
    public Lecture(String writer, String title,
                   String description, Long price, boolean inProgress, boolean discounted) {
        this.writer = writer;
        this.title = title;
        this.description = description;
        this.price = price;
        this.inProgress = inProgress;
        this.discounted = discounted;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setPrice(Long price) {
        this.price = price;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public void setDiscounted(Boolean discounted) {
        this.discounted = discounted;
    }

    public void setLectureThumb(String thumb_path) {
        this.thumb_path = thumb_path;
    }

    public void setLectureDetail(String detail_path) {
        this.detail_path = detail_path;
    }

    public void exist(Lecture lecture) throws Exception {
        if (lecture.getLecture_id() == null) {
            throw new Exception("없는 강의번호입니다.");
        }
    }


}
