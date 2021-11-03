package com.urunner.khweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@Data
@Slf4j
@NoArgsConstructor
public class CommentRes {
    private Long commentNo;
    private Long boardNo;
    private String content;
    private String writer;
    private String name;
    private Long layer;
    private Long groupNo;
    private Date regDate;
}