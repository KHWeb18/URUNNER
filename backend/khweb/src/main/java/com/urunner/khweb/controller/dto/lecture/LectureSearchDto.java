package com.urunner.khweb.controller.dto.lecture;


import com.querydsl.core.annotations.QueryProjection;
import com.urunner.khweb.entity.sort.Category;
import lombok.Data;

import java.util.List;

@Data
public class LectureSearchDto {

    private Long id;
    private String writer;
    private String title;
    private String desc;
    private Long price;
    private boolean inProgress;
    private boolean discounted;
    private String thumbPath;
    private String detailPath;
    private String content;
    private String grade;
    private boolean cart;
    private boolean wishList;
    private List<Category> categoryList;


    @QueryProjection
    public LectureSearchDto(Long id, String writer, String title, String desc,
                            Long price, boolean inProgress, boolean discounted,
                            String thumbPath, String detailPath,String grade) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.inProgress = inProgress;
        this.discounted = discounted;
        this.thumbPath = thumbPath;
        this.detailPath = detailPath;
        this.grade = grade;
    }
}
