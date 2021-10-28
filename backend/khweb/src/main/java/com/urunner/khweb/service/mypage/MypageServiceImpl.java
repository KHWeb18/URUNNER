package com.urunner.khweb.service.mypage;


import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;
import com.urunner.khweb.repository.mypage.MyPageRepository;
import com.urunner.khweb.repository.mypage.TempLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MypageServiceImpl implements MypageService{


    @Autowired
    private MyPageRepository myPageRepository;



    @Override
    public MyNote latestNote() throws Exception {

        //가장 저장된 MyNote객체를 리턴
        MyNote latestNote = myPageRepository.findAll(Sort.by(Sort.Direction.DESC,"regDate")).get(0);
        log.info(latestNote.getTitle());

        return latestNote;
    }

    //Lecture Entity 생성시 데이터타입 변화 필요한 부분 //
    @Autowired
    private TempLectureRepository tempLectureRepository;

    @Override
    public TempLecture latestLec() throws Exception {
        //Entity 변경시 properties부분을 바꾸어 주면됨
        TempLecture latestLecture = tempLectureRepository.findAll(Sort.by(Sort.Direction.DESC,"clickDate")).get(0);
        log.info(latestLecture.getTitle());

        return latestLecture;
    }


}
