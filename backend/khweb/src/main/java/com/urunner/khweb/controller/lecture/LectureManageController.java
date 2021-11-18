package com.urunner.khweb.controller.lecture;


import com.urunner.khweb.service.lecture.LectureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manageLecture")
@Slf4j
public class LectureManageController {

    @Autowired
    private LectureService service;


}
