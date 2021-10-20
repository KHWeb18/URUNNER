package com.urunner.khweb.service.member;

import com.urunner.khweb.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void leaveMember(Long memberNo) throws Exception {
        memberRepository.deleteById(memberNo);
    }
}


