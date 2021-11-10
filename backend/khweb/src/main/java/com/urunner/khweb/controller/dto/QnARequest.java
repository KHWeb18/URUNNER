package com.urunner.khweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Slf4j
@NoArgsConstructor
@DynamicUpdate
public class QnARequest {
    private Long boardNo;
    private String title;
    private String content;
    private String writer;
    private String nickname;
    private String complete;
    private Long currentNum;
    private Long views;
    private Long comments;
}