package com.urunner.khweb.entity.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "studyboard")
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String complete; // 모집 마감 여부

    @Column(length = 100)
    private Long currentNum; // 좋아요~

    @Column(length = 100)
    private Long views; // 조회수

    @Column(length = 100)
    private Long comments; // 댓글수

    @Column(length = 200)
    private String tags; // 댓글수

    @Column(length = 100, nullable = false)
    private Long fit; // 모집 인원

    @Column(length = 200)
    private String notice; // 공지사항

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;

    public Study(String title, String content, String writer, String nickname, String complete, Long currentNum, Long views, Long comments, String tags, Long fit, String notice) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nickname = nickname;
        this.complete = complete;
        this.currentNum = currentNum;
        this.views = views;
        this.comments = comments;
        this.tags = tags;
        this.fit = fit;
        this.notice = notice;
    }
}
