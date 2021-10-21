package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;

public interface MemberService {

    public boolean registerMember(MemberRes memberRes) throws Exception;

    public void leaveMember (Long memberNo) throws Exception;


}
