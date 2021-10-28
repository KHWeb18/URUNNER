package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;

public interface MemberProfileService {
    void register(MemberRes memberRes) throws Exception;
    void update(MemberRes memberRes) throws Exception;
    void update2(MemberRes memberRes) throws Exception;
    String findMyIntroduce(MemberRes memberRes) throws Exception;
}