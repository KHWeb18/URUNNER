package com.urunner.khweb.controller.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.UserDto;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    private HttpSession session;

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


    // 비밀번호 찾기 및 이메일 보내기
    @PostMapping("/findingpw")
    public ResponseEntity<String> findUser(@RequestBody MemberRes memberRes) throws Exception {
        String findUser = memberService.findingUser(memberRes);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    // 비밀번호 재설정하기(이름과 매칭해 비밀번호 찾기)
    @PatchMapping("/changePw/{name}")
    public ResponseEntity<Void> changePw(@PathVariable("name") String name, @RequestBody MemberRes memberRes) throws Exception {
        Member member = memberService.findByName(name);

        memberService.changePw(member, memberRes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/leaveMember")
    public ResponseEntity<Void> leaveMember(@RequestBody UserDto userDto) throws Exception {
        log.info("leavemember()");
        log.info("email입니다 "+userDto.getEmail());
        String email = userDto.getEmail();
        memberService.leaveMember(email);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}