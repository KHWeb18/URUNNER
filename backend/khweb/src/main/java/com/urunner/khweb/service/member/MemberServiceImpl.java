package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.lecture.JoinInstructorDto;
import com.urunner.khweb.entity.lecture.Instructor;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.member.AuthProvider;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.Role;
import com.urunner.khweb.entity.mypage.MyPage;
import com.urunner.khweb.repository.lecture.InstructorRepository;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.repository.member.RoleRepository;
import com.urunner.khweb.repository.mypage.MyPageRepository;
import com.urunner.khweb.utility.MailUtils;
import com.urunner.khweb.utility.PythonRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;



@Slf4j @Transactional @Service @RequiredArgsConstructor @Lazy
public class MemberServiceImpl implements MemberService, UserDetailsService {


    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyPageRepository myPageRepository;
    private final InstructorRepository instructorRepository;
    private final LectureRepository lectureRepository;



    @Override
    public boolean registerMember(MemberRes memberRes) throws Exception {
        Member member = new Member();
        Role role = new Role();
        MyPage myPage = new MyPage(0L);

        //????????? ????????????
        String memberEmail = memberRes.getEmail();
        if (memberRepository.findByEmail(memberEmail) != null) {

            log.info("????????? ?????? ????????????!");
            return false;

        } else {
            //????????????(MemberRes) Entity(Member)?????? ??????
            member.setEmail(memberRes.getEmail());
            member.setNickname(memberRes.getNickname());
            member.setName(memberRes.getNickname());
            member.setPassword(passwordEncoder.encode(memberRes.getPassword()));
            member.setProvider(AuthProvider.local);
            role.setName("ROLE_USER");
            role.setMember(member);
            myPage.setMember(member);

            roleRepository.save(role);
            myPageRepository.save(myPage);
            memberRepository.save(member);

            log.info("????????????");
            return true;
        }
    }

    @Override
    public void leaveMember(String email) throws Exception {

        lectureRepository.deleteAllByWriter(email);
        memberRepository.deleteByEmail(email);


    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        member.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info(member.getRoles().toString());
        log.info(authorities.toString());
        log.info(member.getName());
        log.info(member.getEmail());
        return new org.springframework.security.core.userdetails.User(member.getEmail(), member.getPassword(), authorities);

    }

    @Override
    public void sendMail(String email,String certCode) throws Exception {

        MailUtils.sendMail(email,certCode);

    }
  
    // ???????????? ?????? (?????? ??????)
    @Override
    public String findingUser(MemberRes memberRes) throws Exception {
        String email = memberRes.getEmail();

        Member member = memberRepository.findByEmail(email);

        String name;

        if (member == null) {
            log.info("not find user");
            return "NotFindUser";
        } else {
            name = member.getNickname();

            if (!name.equals(member.getNickname())) {
                log.info("not match id");
                return "NotMatchId";
            }
        }

        String result = new PythonRequest().findUserPw(name, email);

        log.info(result);
        return result;
    }

    // ???????????? ????????? ???????????? ????????? ?????? ???????????? ?????? ?????? (?????? ??? null ??????)
    @Override
    public Member findByName(String name) throws Exception {
        Member member = memberRepository.findByNickname(name);

        if (member == null) {
            log.info("login(): ?????? ?????? ??????.");

        }
            return member;
    }

    // ???????????? ?????????(patch) ??????
    @Override
    public void changePw(Member member, MemberRes memberRes) throws Exception {
        String encodedPassword = passwordEncoder.encode(memberRes.getPassword());
        memberRes.setPassword(encodedPassword);

        member.changePassword(memberRes);

        memberRepository.save(member);
    }

    @Override
    public void getManager(String email) throws Exception {

        Role role = new Role();

        Member member1 = memberRepository.findByEmail(email);
        if (member1 != null) {

            log.info("????????? ???????????????!");

            role.setName("ROLE_MANAGER");
            role.setMember(member1);

            roleRepository.save(role);



            log.info("????????? ?????? ??????");


        }
    }
    @Override
    public boolean joinInstructor(JoinInstructorDto joinInstructorDto) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Role role = new Role();
            Instructor instructor = Instructor.builder()
                    .description(joinInstructorDto.getDescription())
                    .tags(joinInstructorDto.getTags())
                    .build();
            Member member = memberRepository.findByEmail(authentication.getName());


            if (member.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_MANAGER"))) {
                log.info("????????????????????????");
                throw new DuplicateKeyException("?????? ????????? ?????????");
            }

            role.setName("ROLE_MANAGER");
            role.setMember(member);
            instructor.setRole(role);



            roleRepository.save(role);
            instructorRepository.save(instructor);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // ?????? ??????
    @Override
    public List<Member> memberList() throws Exception {
        return memberRepository.findAll();
    }


}


