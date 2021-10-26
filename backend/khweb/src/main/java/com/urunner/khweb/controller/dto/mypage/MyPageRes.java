package com.urunner.khweb.controller.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageRes {

    private String latestLecture;
    private String latestNote;
}
