package com.urunner.khweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class StudyRequest {
    private String email;
    private String name;
    private String password;
    private String introduce;
    private String participant;
    private String state;
}