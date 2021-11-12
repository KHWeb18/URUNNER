package com.urunner.khweb.entity.mypage;


import com.urunner.khweb.entity.lecture.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor @Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myPageId")
    private MyPage myPage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public void setMyPage(MyPage myPage) {
        this.myPage = myPage;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

}
