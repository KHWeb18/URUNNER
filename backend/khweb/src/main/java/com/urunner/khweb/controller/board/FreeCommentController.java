package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.CommentRes;
import com.urunner.khweb.entity.board.Comment;
import com.urunner.khweb.service.board.FreeCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/freeboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class FreeCommentController {

    @Autowired
    private FreeCommentService service;

    @PostMapping("/comment/register")
    public ResponseEntity<Comment> register(@Validated @RequestBody CommentRes commentRes) throws Exception {
        log.info("comment register request from vue");
        log.info("**comment : " + commentRes);

        return new ResponseEntity<>(service.register(commentRes), HttpStatus.OK);
    }


    @GetMapping("/comment/{boardNo}")
    public ResponseEntity<List> getLists (@PathVariable("boardNo") Long boardNo) throws Exception {

        log.info("getLists(): " + service.selectFreeComment(boardNo));

        return new ResponseEntity<>(service.selectFreeComment(boardNo), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentNo}")
    public ResponseEntity<Void> remove(@PathVariable("commentNo") Long commentNo) throws Exception {

        service.delete(commentNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}