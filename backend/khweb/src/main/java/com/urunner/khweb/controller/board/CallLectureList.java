package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.service.board.CallLectureService;
import com.urunner.khweb.service.board.FreeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/callLecture")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CallLectureList {

    @Autowired
    private CallLectureService service;

    @GetMapping("/lists")
    public ResponseEntity<List<Object[]>> getLists () throws Exception {
        log.info("getStudyLists() ");

        return new ResponseEntity<>(service.selectLectureList(), HttpStatus.OK);
    }

    @GetMapping("/lists/{categoryId}")
    public ResponseEntity<List<Object[]>> getLists (@PathVariable("categoryId") Long categoryId) throws Exception {

        return new ResponseEntity<>(service.selectLectureListWithTag(categoryId), HttpStatus.OK);
    }

    @GetMapping("/lists/search/{word}")
    public ResponseEntity<List<Object[]>> getLists (@PathVariable("word") String word) throws Exception {

        return new ResponseEntity<>(service.selectLectureListWithWord(word), HttpStatus.OK);
    }
}