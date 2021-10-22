package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.Role;

import java.util.List;

public interface MemberService {

    public boolean registerMember(MemberRes memberRes) throws Exception;

    public void leaveMember (Long memberNo) throws Exception;

}
