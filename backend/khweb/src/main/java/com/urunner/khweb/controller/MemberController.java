package com.urunner.khweb.controller;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.Member;
import com.urunner.khweb.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/urunner")
@Slf4j
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {


    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody MemberRes memberRes) throws Exception{

        log.info("register()");
        Member member = new Member();
        member.setMypageNo(1L);

        //아이디 중복확인
        String memberEmail = memberRes.getEmail();
        log.info(memberEmail);

        log.info("오류검증");
        String alreaded = memberRepository.findByEmail(memberEmail).getEmail();
        log.info(alreaded);



        if(memberRepository.findByEmail(memberEmail) != null ){
            log.info("가입실패!");


            return new ResponseEntity(false, HttpStatus.OK);


        }else{

            member.setEmail(memberRes.getEmail());
            member.setName(memberRes.getName());
            member.setPassword(memberRes.getPassword());


            memberRepository.save(member);
            log.info("가입성공");
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

    }






}
