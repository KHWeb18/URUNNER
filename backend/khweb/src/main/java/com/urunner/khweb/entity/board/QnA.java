package com.urunner.khweb.entity.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "qnaboard")
public class QnA {
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
    private String name;

    @Column(length = 100, nullable = false)
    private String complete; // 모집 마감 여부

    @Column(length = 100)
    private Long currentNum; // 좋아요~

    @Column(length = 100)
    private Long views; // 조회수

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;

    public QnA(String title, String content, String writer, String name, String complete, Long currentNum, Long views) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.name = name;
        this.complete = complete;
        this.currentNum = currentNum;
        this.views = views;
    }
}