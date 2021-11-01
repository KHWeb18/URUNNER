package com.urunner.khweb.repository.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeRequest {
    private String title;
    private String writer;
    private String content;
    private Date upDate;

}
