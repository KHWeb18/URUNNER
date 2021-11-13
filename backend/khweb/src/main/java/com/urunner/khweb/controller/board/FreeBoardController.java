package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.service.board.FreeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/freeboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class FreeBoardController {

    @Autowired
    private FreeBoardService service;

    @Autowired
    private HttpSession session;

    @PostMapping("/register")
    public ResponseEntity<Free> register(
            @Validated @RequestBody FreeRequest freeRequest) throws Exception {
        log.info("post register request from vue");
        log.info("**board : " + freeRequest);

        return new ResponseEntity<>(service.register(freeRequest), HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<Free>> getLists () throws Exception {
        log.info("getFreeLists() ");

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Optional<Free>> read(@PathVariable("boardNo") Long boardNo) throws Exception {

        Optional<Free> board = service.findByBoardNo(boardNo);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PutMapping ("/{boardNo}")
    public ResponseEntity<Free> modify(@PathVariable("boardNo") Long boardNo,
                                       @Validated @RequestBody FreeRequest freeRequest) throws Exception {
        freeRequest.setBoardNo(boardNo);
        service.updatePost(freeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
        service.delete(boardNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}