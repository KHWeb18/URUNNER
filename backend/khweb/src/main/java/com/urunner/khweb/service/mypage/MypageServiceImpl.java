package com.urunner.khweb.service.mypage;


import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.repository.mypage.MyNoteRepository;
import com.urunner.khweb.repository.mypage.MyPageRepository;
import com.urunner.khweb.repository.mypage.TempLectureRepository;
import com.urunner.khweb.repository.mypage.WishListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MypageServiceImpl implements MypageService{


    @Autowired
    private MyNoteRepository myNoteRepository;

    @Autowired
    private MyPageRepository myPageRepository;

    @Autowired
    private MemberRepository memberRepository;

<<<<<<< HEAD
    @Autowired
    private WishListRepository wishListRepository;

=======
>>>>>>> upstream/main


    @Override
    public MyNote latestNote() throws Exception {

        //가장 저장된 MyNote객체를 리턴
        MyNote latestNote = myNoteRepository.findAll(Sort.by(Sort.Direction.DESC,"regDate")).get(0);
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

    @Override
    public Long getPoint() {
<<<<<<< HEAD
        Authentication authentication = getAuthentication();
=======
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
>>>>>>> upstream/main
//      두번조회
        Member member = memberRepository.findByEmail(authentication.getName());
        return member.getMyPage().getPoint();
    }
<<<<<<< HEAD



    @Override
    public boolean lectureAddToCart(Long lectureId) {

        Authentication authentication = getAuthentication();

//        le
//
//        wishListRepository.

        return false;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
=======
>>>>>>> upstream/main
}
