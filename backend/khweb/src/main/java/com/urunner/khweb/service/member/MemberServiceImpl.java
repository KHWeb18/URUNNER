package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean registerMember(MemberRes memberRes) throws Exception {
        Member member = new Member();

        //아이디 중복확인
        String memberEmail = memberRes.getEmail();
        if (memberRepository.findByEmail(memberEmail) != null) {

            log.info("아이디 중복 가입실패!");
            return false;

        } else {
            //받은요청(MemberRes) Entity(Member)전환 코드
            member.setEmail(memberRes.getEmail());
            member.setName(memberRes.getName());
            member.setPassword(memberRes.getPassword());

            memberRepository.save(member);

            log.info("가입성공");
            return true;
        }



    }

    @Override
    public void leaveMember(Long memberNo) throws Exception {
        memberRepository.deleteById(memberNo);
    }


}


