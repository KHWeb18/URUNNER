//package com.urunner.khweb.controller.board;
//
//import com.urunner.khweb.controller.dto.StudyRequest;
//import com.urunner.khweb.entity.board.Study;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Controller
//@RequestMapping("/studyboard")
//@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
//public class StudyBoardController {
//
//    @Autowired
//    private StudyBoardService service;
//
//    @PostMapping("/register")
//    public ResponseEntity<Study> register(
//            @Validated @RequestBody StudyRequest studyRequest) throws Exception {
//        log.info("post register request from vue");
//        log.info("**board : " + studyRequest);
//
//        return new ResponseEntity<>(service.register(studyRequest), HttpStatus.OK);
//    }
//
//    @GetMapping("/lists")
//    public ResponseEntity<List<Study>> getLists () throws Exception {
//        log.info("getStudyLists() ");
//
//        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{boardNo}")
//    public ResponseEntity<Optional<Study>> read(@PathVariable("boardNo") Long boardNo) throws Exception {
//
//        Optional<Study> board = service.findByBoardNo(boardNo);
//
//        return new ResponseEntity<>(board, HttpStatus.OK);
//    }
//
//    @PutMapping ("/{boardNo}")
//    public ResponseEntity<Study> modify(@PathVariable("boardNo") Long boardNo,
//                                       @Validated @RequestBody StudyRequest studyRequest) throws Exception {
//        studyRequest.setBoardNo(boardNo);
//        service.updatePost(studyRequest);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{boardNo}")
//    public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
//        service.delete(boardNo);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}