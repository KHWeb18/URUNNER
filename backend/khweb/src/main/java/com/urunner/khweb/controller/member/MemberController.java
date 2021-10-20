package com.urunner.khweb.controller.member;

import com.urunner.khweb.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/memberManagement")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    @Autowired
    MemberService memberService;

    // 회원 탈퇴
    @DeleteMapping("/leaveMember/{memberNo}")
    public ResponseEntity<Void> leaveMember(@PathVariable("memberNo")Long memberNo) throws Exception {

        memberService.leaveMember(memberNo);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}