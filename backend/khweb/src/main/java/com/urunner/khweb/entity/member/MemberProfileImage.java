package com.urunner.khweb.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.lecture.PurchasedLecture;
import com.urunner.khweb.entity.mypage.MyPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
@Table(name = "memberProfile")
public class MemberProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_no")
    private Long profileNo;

    private Long memberNo;
    private String thumb_path;


    public MemberProfileImage(Long memberNo, String thumb_path) {

        this.memberNo = memberNo;
        this.thumb_path = thumb_path;

    }

    public void setMemberNo(Long memberNo) {
        this.memberNo = memberNo;
    }

    public void setThumb_path(String thumb_path) {
        this.thumb_path = thumb_path;
    }

    public void setProfileNo(Long profileNo) {
        this.profileNo = profileNo;
    }
}