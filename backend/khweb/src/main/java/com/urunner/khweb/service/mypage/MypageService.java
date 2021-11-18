package com.urunner.khweb.service.mypage;

import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;

public interface MypageService {

    public MyNote latestNote() throws Exception;

    //Lecture Entity생성후 반환타입 바꾸어야함
    public TempLecture latestLec() throws Exception;

    public Long getPoint();

<<<<<<< HEAD
<<<<<<< HEAD
    public boolean lectureAddToCart(Long lectureId);


=======
>>>>>>> upstream/main
=======
    public boolean lectureAddToCart(Long lectureId);

>>>>>>> upstream/main
}
