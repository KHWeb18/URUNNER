package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.InqComment;
import com.urunner.khweb.service.board.InqCommentService;
import com.urunner.khweb.service.member.MemberProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/inqboard")
public class InqCommentController {

    @Autowired
    private InqCommentService service;

    @Autowired
    private MemberProfileService memberProfileService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/comment/register")
    public ResponseEntity<InqComment    > register(@Validated @RequestBody CommentRes commentRes) throws Exception {
        log.info("comment register request from vue");
        log.info("**comment : " + commentRes);

        String writer = commentRes.getWriter();
        Long memberNo = memberProfileService.findMyMemberNo(writer);
        String thumbPath = memberProfileService.findMyThumbPath(memberNo);

        commentRes.setThumb_path(thumbPath);

        service.register(commentRes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comment/{boardNo}")
    public ResponseEntity<List<InqComment>> getLists (@PathVariable("boardNo") Long boardNo) throws Exception {

        return new ResponseEntity<>(service.selectInqComment(boardNo), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/comment/{commentNo}")
    public ResponseEntity<Void> remove(@PathVariable("commentNo") Long commentNo) throws Exception {

        service.delete(commentNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}