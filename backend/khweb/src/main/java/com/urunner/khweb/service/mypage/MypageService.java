package com.urunner.khweb.service.mypage;

import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;

public interface MypageService {

    public MyNote latestNote() throws Exception;

    //Lecture Entity생성후 반환타입 바꾸어야함
    public TempLecture latestLec() throws Exception;

}
