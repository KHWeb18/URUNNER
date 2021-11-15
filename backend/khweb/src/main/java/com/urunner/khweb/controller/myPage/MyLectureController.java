//package com.urunner.khweb.controller.myPage;
//
//import com.urunner.khweb.controller.dto.board.QnARequest;
//import com.urunner.khweb.entity.board.QnA;
//import com.urunner.khweb.entity.board.QnAMember;
//import com.urunner.khweb.entity.lecture.Lecture;
//import com.urunner.khweb.service.board.QnABoardService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Controller
//@RequestMapping("/qnaboard")
//@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
//public class MyLectureController {
//
//    @Autowired
//    private MyLectureService service;
//
//    @GetMapping("/lists")
//    public ResponseEntity<List<Lecture>> getLists () throws Exception {
//        log.info("getStudyLists() ");
//
//        return new ResponseEntity<>(service.selectMyLectureList(), HttpStatus.OK);
//    }
//}