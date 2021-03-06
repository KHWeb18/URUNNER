package com.urunner.khweb.controller.dto.lecture;

import com.urunner.khweb.entity.sort.Category;
import com.urunner.khweb.service.lecture.GetReviewDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class LectureDto {

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
    private List<Category> category;
    private boolean cart;
    private boolean purchased;

    private boolean wishList;
    private GetReviewDto getReviewDto;

    public LectureDto(Long id, String writer, String title, String desc, Long price, boolean inProgress, boolean discounted, String thumbPath, String detailPath, String content, String grade, List<Category> category) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.inProgress = inProgress;
        this.discounted = discounted;
        this.thumbPath = thumbPath;
        this.detailPath = detailPath;
        this.category = category;
        this.content = content;
        this.grade = grade;
    }

    public LectureDto(Long id, String writer, String title, String desc, Long price, boolean inProgress, boolean discounted, String thumbPath, String detailPath, String content, String grade, List<Category> category, GetReviewDto getReviewDto) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.inProgress = inProgress;
        this.discounted = discounted;
        this.thumbPath = thumbPath;
        this.detailPath = detailPath;
        this.category = category;
        this.content = content;
        this.grade = grade;
        this.getReviewDto = getReviewDto;
    }

    public LectureDto(Long id, String writer, String title, Long price, boolean discounted, String thumbPath) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.price = price;
        this.discounted = discounted;
        this.thumbPath = thumbPath;
    }
}
