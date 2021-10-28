package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;

public interface MemberService {

    public boolean registerMember(MemberRes memberRes) throws Exception;

    public void leaveMember (String email) throws Exception;

    //public boolean sendEmail(MemberRes memberRes) throws Exception;

    // 비밀번호 찾기 및 이메일 보내기
    String findingUser(MemberRes memberRes) throws Exception;
    // 비밀번호 변경(DB 이름과 비밀번호 일치 검사)
    Member findByName(String name) throws Exception;
    // 비밀번호 변경
    void changePw(Member member, MemberRes memberRes) throws Exception;

}
