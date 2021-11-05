package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.StudyRequest;
import com.urunner.khweb.entity.board.Study;
import com.urunner.khweb.entity.board.StudyMember;
import com.urunner.khweb.service.board.StudyBoardService;
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
@RequestMapping("/studyboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class StudyBoardController {

    @Autowired
    private StudyBoardService service;

    @PostMapping("/register")
    public ResponseEntity<Study> register(
            @Validated @RequestBody StudyRequest studyRequest) throws Exception {
        log.info("post register request from vue");
        log.info("**board : " + studyRequest);

        return new ResponseEntity<>(service.register(studyRequest), HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<Study>> getLists () throws Exception {
        log.info("getStudyLists() ");

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Optional<Study>> read(@PathVariable("boardNo") Long boardNo) throws Exception {

        Optional<Study> board = service.findByBoardNo(boardNo);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping("/memberList/{boardNo}") // study에 지원한 memberList 요청
    public ResponseEntity<List<StudyMember>> memberList(@PathVariable("boardNo") Long boardNo){

        log.info("****");
        System.out.println(boardNo);
        log.info("****");
        log.info("****");
        log.info("****");
        log.info("****");
        List<StudyMember> member = service.selectStudyBoardNo(boardNo);
        log.info("****");
        log.info("****");
        log.info("study memberList 값은 : " + member);
        log.info("****");
        log.info("****");

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/apply/{boardNo}") // study에 지원한 member 등록
    public ResponseEntity<String> apply(@PathVariable("boardNo") Long boardNo,
                                          @Validated @RequestBody StudyMember studyMember) throws Exception {
        System.out.println("######## Controller run success");
        studyMember.setBoardNo(boardNo);
        System.out.println("######## applyMember ready");
        service.applyMember(studyMember); //일단 memberList에 추가하고(중복체크까지)
        System.out.println("######## applyMember success");


        System.out.println("######## checkLength ready");
        Long num = service.checkLength(boardNo);// boardNO 값을 가지는 테이블의 length를 구해서 num으로 주고
        System.out.println("######## checkLength success and num value is" + num);
        num += 1;

        StudyRequest studyRequest = new StudyRequest();
        studyRequest.setCurrentNum(num);
        studyRequest.setBoardNo(boardNo);
        System.out.println("######## studyRequest value :" + studyRequest);
        service.updateCurrentNum(studyRequest); // post 값 갱신

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping ("/{boardNo}")
    public ResponseEntity<Study> modify(@PathVariable("boardNo") Long boardNo,
                                       @Validated @RequestBody StudyRequest studyRequest) throws Exception {
        studyRequest.setBoardNo(boardNo);
        service.updatePost(studyRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
        service.delete(boardNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}