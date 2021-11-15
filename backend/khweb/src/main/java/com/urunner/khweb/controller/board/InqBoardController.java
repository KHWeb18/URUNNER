package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.InqRequest;
import com.urunner.khweb.entity.board.Inq;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.service.board.InqBoardService;
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
@RequestMapping("/inqboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class InqBoardController {

    @Autowired
    private InqBoardService service;

    @PostMapping("/register")
    public ResponseEntity<Inq> register(
            @Validated @RequestBody InqRequest inqRequest) throws Exception {
        log.info(":::: post register request from vue");
        log.info(":::: RequestBody value : " + inqRequest);

        return new ResponseEntity<>(service.register(inqRequest), HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<Inq>> getLists () throws Exception {
        log.info("getInqLists() ");
//        log.info("inq value : " + inq);

        return new ResponseEntity<>(service.selectInqList(), HttpStatus.OK);
    }

    @GetMapping("/lists/1/{userId}") //유저용 화면
    public ResponseEntity<List<Inq>> getListsForUser (@PathVariable("userId") String userId) throws Exception {
        log.info("getInqLists() ");
        log.info("getUserId " + userId);

        return new ResponseEntity<>(service.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/lists/{complete}")
    public ResponseEntity<List<Inq>> getListsWithFilter (@PathVariable("complete") String complete) throws Exception {
        log.info("complete value : " + complete);

        return new ResponseEntity<>(service.findByComplete(complete), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Optional<Inq>> read(@PathVariable("boardNo") Long boardNo) throws Exception {

        Optional<Inq> board = service.findByBoardNo(boardNo);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PutMapping("/apply/{boardNo}") // study에 지원한 member 등록 ->>> 다른 게시판에서는 좋아요로 활용
    public ResponseEntity<String> apply(@PathVariable("boardNo") Long boardNo,
                                        @Validated @RequestBody QnAMember qnAMember) throws Exception {
        qnAMember.setBoardNo(boardNo);
        service.applyMember(qnAMember); //일단 memberList에 추가하고(중복체크까지)

        Long num = service.checkLength(boardNo);// boardNO 값을 가지는 테이블의 length를 구해서 num으로 주고
        num += 1;

        InqRequest inqRequest = new InqRequest();
        inqRequest.setCurrentNum(num);
        inqRequest.setBoardNo(boardNo);
        service.updateCurrentNum(inqRequest); // post 값 갱신

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping ("/{boardNo}")
    public ResponseEntity<Inq> modify(@PathVariable("boardNo") Long boardNo,
                                      @Validated @RequestBody InqRequest inqRequest) throws Exception {
        log.info(":::: post modify request from vue");
        log.info(":::: RequestBody value : " + inqRequest);

        inqRequest.setBoardNo(boardNo);
        service.updatePost(inqRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
        service.delete(boardNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}