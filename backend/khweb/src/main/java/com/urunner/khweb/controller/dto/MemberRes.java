package com.urunner.khweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class MemberRes {


    private String email;

    private String name;    //nickname으로 바뀌면 없어질부분

    private String nickname;

    private String password;

    private String introduce;


}