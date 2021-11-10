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

    @Column(length = 100, nullable = false)
    private Long fit; // 모집 인원

    @Column(length = 100, nullable = false)
    private Long currentNum; // 현재 지원한 인원은 몇 명?

    @CreationTimestamp
    private Date regDate;

    public Study(String title, String content, String writer, String nickname, String complete, Long fit, Long currentNum) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nickname = nickname;
        this.complete = complete;
        this.fit = fit;
        this.currentNum = currentNum;
    }
}