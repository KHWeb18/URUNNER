package com.urunner.khweb.controller.dto.mypage;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class ProfileRes {
    private String email;
    private String introduce;
    private Long member_no;
    private String thumb_path;
    private Long profile_no;
}