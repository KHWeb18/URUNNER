package com.urunner.khweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class MemberRes {


    private String email;

    private String name;

    private String password;


}