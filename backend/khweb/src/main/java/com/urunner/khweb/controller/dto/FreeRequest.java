package com.urunner.khweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class FreeRequest {
    private Long boardNo;
    private String title;
    private String content;
    private String writer;
    private String nickname;
}