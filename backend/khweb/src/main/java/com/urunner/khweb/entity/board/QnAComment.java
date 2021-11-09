package com.urunner.khweb.entity.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "qnacomment")
public class QnAComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    private Long commentNo;

    @Column(length = 100, nullable = false)
    private Long boardNo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private Long layer;

    @Column(length = 100)
    private Long groupNo; // 대댓글이 달리는 코멘트 번호

    @CreationTimestamp
    private Date regDate;

    public QnAComment(Long boardNo, String content, String writer, String name, Long layer, Long groupNo) {
        this.boardNo = boardNo;
        this.content = content;
        this.writer = writer;
        this.name = name;
        this.layer = layer;
        this.groupNo = groupNo;
    }


}
