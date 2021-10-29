package com.urunner.khweb.controller.member;

import com.urunner.khweb.controller.dto.MailDto;
import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.UserDto;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.service.member.MemberService;
import com.urunner.khweb.service.member.SendEmailServiceImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/memberManagement")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    //@Autowired
    //private JavaMailSender javaMailSender;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    //회원가입
    @PostMapping("/register-member")
    public ResponseEntity<Boolean> register(@RequestBody MemberRes memberRes) throws Exception {

        log.info("register()");

        boolean isOk = memberService.registerMember(memberRes);

        if (isOk) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

    }

    @GetMapping("/test")
    public List<Member> test() {

        return memberRepository.findAll();
    }
/*
    // 메일 발송
    @PostMapping("/send")
    @Value("${spring.mail.username}")
    public String index() throws MessagingException, UnsupportedEncodingException {

        String to = "wsk0307@naver.com";  // 받는 사람
        String from = "wsk0307@gmail.com";  // 보내는 사람
        String subject = "유러너 임시 비밀번호 입니다.";

        StringBuilder body = new StringBuilder();
        body.append("<html><body><h1> 고객 임시 비밀번호 안내 </h1>"); // 제목
        body.append("<div>임시비밀 번호 :  </div> </body></html>"); // 본문

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

        mimeMessageHelper.setFrom(from,"유러너");  // 보내는 사람 이름
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);

        // 파일 첨부 기능
        //FileSystemResource fileSystemResource = new FileSystemResource(new File("C:/Users/HOME/Desktop/test.txt"));
        //mimeMessageHelper.addAttachment("또르르.txt", fileSystemResource);

        //FileSystemResource file = new FileSystemResource(new File("C:/Users/HOME/Desktop/flower.jpg"));
        //mimeMessageHelper.addInline("flower.jpg", file);

        javaMailSender.send(message);

        return "완료";
    }
    */



    // 회원 탈퇴
    @DeleteMapping("/leaveMember")
    public ResponseEntity<Void> leaveMember() throws Exception {

        log.info("leavemember()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //log.info(principal.toString());

        memberService.leaveMember(authentication.getName());

        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}