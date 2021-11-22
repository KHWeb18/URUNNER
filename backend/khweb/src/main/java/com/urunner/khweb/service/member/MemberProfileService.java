package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.mypage.ProfileRes;

import java.util.List;
import java.util.Optional;

public interface MemberProfileService {
    void register(MemberRes memberRes) throws Exception;
    void update(MemberRes memberRes) throws Exception;
    void update2(MemberRes memberRes) throws Exception;
    String findMyIntroduce(String email) throws Exception;
    Long findMyMemberNo(String email) throws Exception;
    String findMyThumbPath(Long member_no) throws Exception;
    Long findMyProfileNo(Long member_no) throws Exception;
    void delete(Long profileNo) throws Exception;
    List<ProfileRes> findMyProfile(String email) throws Exception;
    void lectureAddImage(String thum, Long id);

//    void deleteImage(Long member_no) throws Exception;
}