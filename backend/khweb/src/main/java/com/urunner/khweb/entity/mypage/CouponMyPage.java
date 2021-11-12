package com.urunner.khweb.entity.mypage;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CouponMyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponMyPageId;

    private boolean used;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myPageId")
    private MyPage myPage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponId")
    private Coupon coupon;

    public void setMyPage(MyPage myPage) {
        this.myPage = myPage;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }


}
