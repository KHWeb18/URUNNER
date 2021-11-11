package com.urunner.khweb.entity.mypage;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class MyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myPageId;

    private Long point;

    @OneToMany(mappedBy = "myPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "myPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponMyPage> couponList = new ArrayList<>();
//  회원가입시 맵핑
    @Builder
    public MyPage(Long point) {
        this.point = point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

}
