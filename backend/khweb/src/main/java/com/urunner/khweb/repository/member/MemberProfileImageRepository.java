package com.urunner.khweb.repository.member;

import com.urunner.khweb.controller.dto.mypage.ProfileRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.MemberProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberProfileImageRepository extends JpaRepository<MemberProfileImage, Long> {

    @Query(value = "select thumb_path from member_profile where member_no = :member_no", nativeQuery = true)
    String findMyThumbPath(Long member_no);

    @Query(value = "select profile_no from member_profile where member_no = :member_no", nativeQuery = true)
    Long findMyProfileNo(Long member_no);

//    @Query(value = "delete from member_profile where profile_no = :profileNO", nativeQuery = true)
//    void deleteImage(Long profileNO);
}