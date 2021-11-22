package com.urunner.khweb.service.mypage;

import com.urunner.khweb.controller.dto.lecture.DtoWrapper3;
import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;

import javax.naming.AuthenticationException;

public interface MypageService {

    public MyNote latestNote() throws Exception;

    //Lecture Entity생성후 반환타입 바꾸어야함
    public TempLecture latestLec() throws Exception;

    public Long getPoint();

    public boolean lectureAddToWish(Long lectureId) throws AuthenticationException;

    public Boolean lectureAddCart(Long lectureId) throws AuthenticationException;

    public DtoWrapper3 addToWishInLecture(Long lectureId) throws AuthenticationException;
}
