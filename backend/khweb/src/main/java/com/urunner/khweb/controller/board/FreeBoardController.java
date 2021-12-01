package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.service.board.FreeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/freeboard")
public class FreeBoardController {

    @Autowired
    private FreeBoardService service;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/register")
    public ResponseEntity<Free> register(
            @Validated @RequestBody FreeRequest freeRequest) throws Exception {
        log.info(":::: post register request from vue");
        log.info(":::: RequestBody value : " + freeRequest);

        return new ResponseEntity<>(service.register(freeRequest), HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<Free>> getLists () throws Exception {
        log.info("getStudyLists() ");

        return new ResponseEntity<>(service.selectFreeList(), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Optional<Free>> read(@PathVariable("boardNo") Long boardNo) throws Exception {

        Optional<Free> board = service.findByBoardNo(boardNo);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/apply/{boardNo}") // study에 지원한 member 등록
    public ResponseEntity<String> apply(@PathVariable("boardNo") Long boardNo,
                                        @Validated @RequestBody QnAMember qnAMember) throws Exception {
        System.out.println("######## Controller run success");
        qnAMember.setBoardNo(boardNo);
        System.out.println("######## applyMember ready");
        service.applyMember(qnAMember); //일단 memberList에 추가하고(중복체크까지)
        System.out.println("######## applyMember success");

        System.out.println("######## checkLength ready");
        Long num = service.checkLength(boardNo);// boardNO 값을 가지는 테이블의 length를 구해서 num으로 주고
        System.out.println("######## checkLength success and num value is" + num);
        num += 1;

        FreeRequest freeRequest = new FreeRequest();
        freeRequest.setCurrentNum(num);
        freeRequest.setBoardNo(boardNo);
        System.out.println("######## studyRequest value :" + freeRequest);
        service.updateCurrentNum(freeRequest); // post 값 갱신

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping ("/{boardNo}")
    public ResponseEntity<Free> modify(@PathVariable("boardNo") Long boardNo,
                                      @Validated @RequestBody FreeRequest freeRequest) throws Exception {
        log.info(":::: post modify request from vue");
        log.info(":::: RequestBody value : " + freeRequest);

        freeRequest.setBoardNo(boardNo);
        service.updatePost(freeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
        service.delete(boardNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}