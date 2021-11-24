package com.urunner.khweb.service.mypage;


import com.urunner.khweb.controller.dto.lecture.DtoWrapper3;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.PurchasedLecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.Cart;
import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;
import com.urunner.khweb.entity.mypage.WishList;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.lecture.PurchasedLectureRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.repository.mypage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@Slf4j
public class MypageServiceImpl implements MypageService{


    @Autowired
    private MyNoteRepository myNoteRepository;

    @Autowired
    private MyPageRepository myPageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private CartRepository cartRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PurchasedLectureRepository purchasedLectureRepository;


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
        Authentication authentication = getAuthentication();
//      두번조회
        Member member = memberRepository.findByEmail(authentication.getName());
        return member.getMyPage().getPoint();
    }



    @Override
    public boolean lectureAddToWish(Long lectureId) throws AuthenticationException {

        boolean exist = false;

        Authentication authentication = getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            log.info("로그인 되있지않은 사용자");
            throw new AuthenticationException("로그인 되있지않은 사용자");
        }

        Member member = memberRepository.findByEmail(authentication.getName());

        List<WishList> collect = new ArrayList<>(member.getMyPage().getWishLists());

        for (WishList list : collect) {
            if (Objects.equals(list.getLecture().getLecture_id(), lectureId)) {
                member.getMyPage().getWishLists().removeIf(w -> w.getWishListId().equals(list.getWishListId()));
                wishListRepository.deleteById(list.getWishListId());
                log.info("이미 등록된 위시리스트" + lectureId.toString());
                exist = true;
            }
        }

        if (!exist) {
            Lecture lecture = em.find(Lecture.class, lectureId);

            WishList wishList = new WishList();

            wishList.setLecture(lecture);
            wishList.setMyPage(member.getMyPage());

            wishListRepository.save(wishList);
        }

        return !exist;
    }


    @Override
    public Boolean lectureAddCart(Long lectureId) throws AuthenticationException {

        boolean exist = false;

        Authentication authentication = getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            log.info("로그인 되있지않은 사용자");
            throw new AuthenticationException("로그인 되있지않은 사용자");
        }

        Member member = memberRepository.findByEmail(authentication.getName());

        List<PurchasedLecture> PurchasedLectureList = purchasedLectureRepository.findByMemberNo(member.getMemberNo());
        Lecture lecture1 = em.find(Lecture.class, lectureId);

        for (PurchasedLecture purchasedLecture : PurchasedLectureList) {
            if (purchasedLecture.getLecture_id().equals(lecture1.getLecture_id())) {
                log.info("이미 구매한 강의입니다.");
                return null;
            }
        }


        List<Cart> collect = new ArrayList<>(member.getMyPage().getCartList());

        for (Cart list : collect) {
            if (Objects.equals(list.getLecture().getLecture_id(), lectureId)) {
                member.getMyPage().getCartList().removeIf(w -> w.getId().equals(list.getId()));
                cartRepository.deleteById(list.getId());
                log.info("이미 담긴 강의" + lectureId.toString());
                exist = true;
            }
        }

        if (!exist) {
            Lecture lecture = lecture1;

            Cart cart = new Cart();

            cart.setLecture(lecture);
            cart.setMyPage(member.getMyPage());

            cartRepository.save(cart);
        }

        return !exist;
    }

    @Override
    public DtoWrapper3 addToWishInLecture(Long lectureId) throws AuthenticationException {

        boolean exist = false;

        Authentication authentication = getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            log.info("로그인 되있지않은 사용자");
            throw new AuthenticationException("로그인 되있지않은 사용자");
        }

        Member member = memberRepository.findByEmail(authentication.getName());

        List<WishList> collect = new ArrayList<>(member.getMyPage().getWishLists());

        for (WishList list : collect) {
            if (Objects.equals(list.getLecture().getLecture_id(), lectureId)) {
                member.getMyPage().getWishLists().removeIf(w -> w.getWishListId().equals(list.getWishListId()));
                wishListRepository.deleteById(list.getWishListId());
                log.info("이미 등록된 위시리스트" + lectureId.toString());
                exist = true;
            }
        }

        Lecture lecture = em.find(Lecture.class, lectureId);
        if (!exist) {

            WishList wishList = new WishList();

            wishList.setLecture(lecture);
            wishList.setMyPage(member.getMyPage());

            wishListRepository.save(wishList);
        }

        String query3 = "select count(w.wishListId) from WishList w where w.lecture.lecture_id = :id";

        Long wishListCount = em.createQuery(query3, Long.class)
                .setParameter("id", lecture.getLecture_id())
                .getSingleResult();

        System.out.println("위시리스트 수" + wishListCount.toString());

        DtoWrapper3 dtoWrapper = new DtoWrapper3();
        dtoWrapper.setWishListCount(wishListCount);
        dtoWrapper.setExist(!exist);

        return dtoWrapper;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
