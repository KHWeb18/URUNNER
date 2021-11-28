package com.urunner.khweb.utility;


import com.urunner.khweb.controller.dto.lecture.LectureDto;
import com.urunner.khweb.controller.dto.lecture.LectureOrderByReviewDto;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.PurchasedLecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.Cart;
import com.urunner.khweb.entity.mypage.WishList;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component
public class LectureUtil {

    @Value("${image.location}")
    String imageLocation;

//    new인스턴스화 하면 스프링이 관여하지않아서 어노테이션이 무시된다.
//    따라서 ioc를 두고 @AutoWired를 사용하면 해결할수있다.

    @Value("${video.location}")
    String videoLocation;


    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    MemberRepository memberRepository;


    @Transactional
    public void deleteUtil(String form, String delPath){

        ClassPathResource resource;

        if (form.equals("image")) {
            ClassPathResource resource1 = new ClassPathResource(imageLocation);
            resource = resource1;
        } else {
            ClassPathResource resource2 = new ClassPathResource(videoLocation);
            resource = resource2;
        }

        try {
            Path path = Paths.get(resource.getURI());
            String dirPath = path.toString() + "/" + authentication() + "/" + delPath;
            System.out.println("위치 디렉토리 : " + dirPath);

            File file = new File(dirPath);

            System.out.println(dirPath);

            if (file.exists()) {
//                원본파일이 잡혀있어서 삭제가 안되는경우
//                file.delete();
                System.gc();
                System.runFinalization();
                boolean delete = file.delete();
                System.out.println("삭제여부" + delete);
            }else {
                System.out.println("파일이 존재하지 않는다.");
            }

        } catch (Exception e) {
            System.out.println("삭제에러");
        }

    }

    public void isPurchasedLecture(Long videoId, String name) {
        try {
            Optional<Lecture> lectureFromVideo = lectureRepository.getLectureFromVideo(videoId);
            Member member = memberRepository.findByEmail(name);
            Optional<Member> isPurchased = memberRepository.getIsPurchased(member.getMemberNo(), lectureFromVideo.get().getLecture_id());
            boolean present = isPurchased.isPresent();
            if (!present) {
                throw new NoSuchElementException("수강생아닙니다.");
            }
        } catch (NoSuchElementException no) {
            throw no;
        }
    }
    public void isCartList(Page<LectureDto> lectureDtos, List<Cart> carts) {
        if (carts.size() != 0) {
            for (int i = 0; i < lectureDtos.getContent().size(); i++) {
                for (int j = 0; j < carts.size(); j++) {
                    boolean exist = lectureDtos.getContent().get(i).getId().equals(carts.get(j).getLecture().getLecture_id());
                    System.out.println("매칭 여부 확인 : " + exist);
                    if (exist) {
                        lectureDtos.getContent().get(i).setCart(true);
                    }
                }
            }
        }
    }

    public void isCartListReview(Page<LectureOrderByReviewDto> lectureDtos, List<Cart> carts) {
        if (carts.size() != 0) {
            for (int i = 0; i < lectureDtos.getContent().size(); i++) {
                for (int j = 0; j < carts.size(); j++) {
                    boolean exist = lectureDtos.getContent().get(i).getId().equals(carts.get(j).getLecture().getLecture_id());
                    System.out.println("매칭 여부 확인 : " + exist);
                    if (exist) {
                        lectureDtos.getContent().get(i).setCart(true);
                    }
                }
            }
        }
    }

    public void isWishList(Page<LectureDto> lectureDtos, List<WishList> wishLists) {
        if (wishLists.size() != 0) {
            for (int i = 0; i < lectureDtos.getContent().size(); i++) {
                for (int j = 0; j < wishLists.size(); j++) {
                    boolean exist = lectureDtos.getContent().get(i).getId().equals(wishLists.get(j).getLecture().getLecture_id());
                    System.out.println("매칭 여부 확인 : " + exist);
                    if (exist) {
                        lectureDtos.getContent().get(i).setWishList(true);
                    }
                }
            }
        }
    }

    public void isWishListReview(Page<LectureOrderByReviewDto> lectureDtos, List<WishList> wishLists) {
        if (wishLists.size() != 0) {
            for (int i = 0; i < lectureDtos.getContent().size(); i++) {
                for (int j = 0; j < wishLists.size(); j++) {
                    boolean exist = lectureDtos.getContent().get(i).getId().equals(wishLists.get(j).getLecture().getLecture_id());
                    System.out.println("매칭 여부 확인 : " + exist);
                    if (exist) {
                        lectureDtos.getContent().get(i).setWishList(true);
                    }
                }
            }
        }
    }

    public void isWishList(Optional<Lecture> lecture, Optional<LectureDto> lectureDto, List<WishList> wishLists) {
        if (wishLists.size() != 0) {
            for (int j = 0; j < wishLists.size(); j++) {
                boolean exist = lecture.get().getLecture_id().equals(wishLists.get(j).getLecture().getLecture_id());
                System.out.println("매칭 여부 확인 : " + exist);
                if (exist) {
                    lectureDto.ifPresent(l -> l.setWishList(true));
                }
            }
        }
    }

    public void isPurchasedList(Optional<Lecture> lecture, Optional<LectureDto> lectureDto, List<PurchasedLecture> purchasedLectureList) {
        if (purchasedLectureList.size() != 0) {
            for (int i = 0; i < purchasedLectureList.size(); i++) {
                boolean exist = lecture.get().getLecture_id().equals(purchasedLectureList.get(i).getLecture_id());
                System.out.println("구매여부 확인 : " + exist);
                if (exist) {
                    lectureDto.ifPresent(l -> l.setPurchased(true));
                }
            }
        }
    }

    public void isCartList(Optional<Lecture> lecture, Optional<LectureDto> lectureDto, List<Cart> carts) {
        if (carts.size() != 0) {
            for (int j = 0; j < carts.size(); j++) {
                boolean exist = lecture.get().getLecture_id().equals(carts.get(j).getLecture().getLecture_id());
                System.out.println("매칭 여부 확인 : " + exist);
                if (exist) {
                    lectureDto.ifPresent(l -> l.setCart(true));
                }
            }
        }
    }

    public String authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    public void isProgress(Long id, boolean isProgress) {

        Optional<Lecture> lecture = lectureRepository.findById(id);

        lecture.orElseThrow(() -> new NoSuchElementException());

        lecture.ifPresent(l -> {
            l.setInProgress(isProgress);
            lectureRepository.save(l);
        });
    }

}
