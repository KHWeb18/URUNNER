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
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    private String email;
    private String name;
    private String password;
    private String introduce;
    private String nickname;

    @Column(name = "is_cert",columnDefinition = "boolean default false")
    private boolean isCert;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "member_no")
    private List<PurchasedLecture> purchasedLectureList = new ArrayList<>();

    public void addPurchasedLecture(PurchasedLecture purchasedLecture){
        purchasedLecture.setMemberNo(this.memberNo);
        purchasedLectureList.add(purchasedLecture);
    }


    public Member(String email, String password, String name, String introduce,String nickname) {

        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.name = name;
        this.introduce = introduce;

    }
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", orphanRemoval = true)
    private Collection<Role> roles = new ArrayList<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "member", orphanRemoval = true)
    private MyPage myPage;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname){this.nickname = nickname;}


    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    // 비밀번호 재설정
    public void changePassword(MemberRes memberRes) {
        this.password = memberRes.getPassword();
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public void setCert(Boolean bool){
        this.isCert = bool;
    }
}