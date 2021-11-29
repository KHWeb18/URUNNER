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

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/memberManagement")
public class MemberController {

    //@Autowired
    //private JavaMailSender javaMailSender;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

//    private HttpSession session;

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

    // 회원 탈퇴

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/leaveMember")
    public ResponseEntity<Void> leaveMember() throws Exception {

        log.info("leavemember()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //log.info(principal.toString());

        memberService.leaveMember(authentication.getName());

        return null;

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

    @PatchMapping("/getManager/{email}")
    public ResponseEntity<Void> getManager(@PathVariable("email")String email) throws Exception {

        //Member member = memberService.findByEmail(email);
        memberService.getManager(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    // 회원 조회
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/memberList")
    public ResponseEntity<List<Member>> getMemberList () throws Exception {

        List<Member> list = memberService.memberList();

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}