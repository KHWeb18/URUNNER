package com.urunner.khweb.controller.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.QnAComment;
import com.urunner.khweb.service.board.QnABoardService;
import com.urunner.khweb.service.board.QnACommentService;
import com.urunner.khweb.service.member.MemberProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/qnaboard")
public class QnACommentController {

    @Autowired
    private QnACommentService service;

    @Autowired
    private QnABoardService boardService;

    @Autowired
    private MemberProfileService memberProfileService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/comment/register")
    public ResponseEntity<QnAComment> register(@Validated @RequestBody CommentRes commentRes) throws Exception {
        log.info("comment register request from vue");
        log.info("**comment : " + commentRes);

        String writer = commentRes.getWriter();
        Long memberNo = memberProfileService.findMyMemberNo(writer);
        String thumbPath = memberProfileService.findMyThumbPath(memberNo);

        commentRes.setThumb_path(thumbPath);
        boardService.updateComments(commentRes.getBoardNo(), 1L);

        service.register(commentRes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comment/{boardNo}")
    public ResponseEntity<List<QnAComment>> getLists (@PathVariable("boardNo") Long boardNo) throws Exception {
        log.info(":::: comment getLists");
        service.selectStudyComment(boardNo);

        return new ResponseEntity<>(service.selectStudyComment(boardNo), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/comment/{boardNo}/{commentNo}")
    public ResponseEntity<Void> remove(@PathVariable("commentNo") Long commentNo,
                                       @PathVariable("boardNo") Long boardNo) throws Exception {
        service.delete(commentNo);
        boardService.updateComments(boardNo, -1L);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}