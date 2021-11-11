package com.urunner.khweb.entity.mypage;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @NoArgsConstructor @Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private Long discount;

    private String name;

    private Date due;

    private boolean isExpired;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponMyPage> couponMyPage = new ArrayList<>();

    @Builder
    public Coupon(Long discount, String name, Date due, boolean isExpired) {
        this.discount = discount;
        this.name = name;
        this.due = due;
        this.isExpired = isExpired;
    }

}
