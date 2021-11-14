package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.InqComment;
import com.urunner.khweb.service.board.InqCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/inqboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class InqCommentController {

    @Autowired
    private InqCommentService service;

    @PostMapping("/comment/register")
    public ResponseEntity<InqComment    > register(@Validated @RequestBody CommentRes commentRes) throws Exception {

        service.register(commentRes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comment/{boardNo}")
    public ResponseEntity<List<InqComment>> getLists (@PathVariable("boardNo") Long boardNo) throws Exception {

        return new ResponseEntity<>(service.selectInqComment(boardNo), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentNo}")
    public ResponseEntity<Void> remove(@PathVariable("commentNo") Long commentNo) throws Exception {

        service.delete(commentNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}