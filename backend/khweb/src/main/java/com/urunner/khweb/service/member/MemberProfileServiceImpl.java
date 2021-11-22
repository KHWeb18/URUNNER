package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.mypage.ProfileRes;
import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.MemberProfileImage;
import com.urunner.khweb.repository.member.MemberProfileImageRepository;
import com.urunner.khweb.repository.member.MemberProfileRepository;
import com.urunner.khweb.utility.LectureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberProfileServiceImpl implements MemberProfileService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberProfileRepository repository;

    @Autowired
    private MemberProfileImageRepository imageRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private LectureUtil lectureUtil;

    @Override
    public void register(MemberRes memberRes) throws Exception {

        Member memberEntity = new Member();
        System.out.println(memberEntity);

        repository.save(memberEntity);
    }

    @Override
    public void update(MemberRes memberRes) throws Exception {

        String nickname = memberRes.getNickname();
        String email = memberRes.getEmail();
        String introduce = memberRes.getIntroduce();

        System.out.println("Repository STEP(no password)");
        System.out.println("memberEntity.email : " + email);
        System.out.println("memberEntity.name : " + nickname);
        System.out.println("memberEntity.introduce : " + introduce);

        repository.update(nickname, email, introduce);
    }

    @Override
    public void update2(MemberRes memberRes) {

        String nickname = memberRes.getNickname();
        String email = memberRes.getEmail();
        String password = passwordEncoder.encode(memberRes.getPassword());
        String introduce = memberRes.getIntroduce();

        System.out.println("Repository STEP(with password)");
        System.out.println("memberEntity.email : " + email);
        System.out.println("memberEntity.name : " + nickname);
        System.out.println("memberEntity.password : " + password);
        System.out.println("memberEntity.introduce : " + introduce);

        repository.update2(nickname, email, password, introduce);
    }

    @Override
    public String findMyIntroduce(String email) {
        return repository.findMyIntroduce(email);
    }

    @Override
    public Long findMyMemberNo(String email) {
        return repository.findMyMemberNo(email);
    }

    @Override
    public String findMyThumbPath(Long member_no) {
        return imageRepository.findMyThumbPath(member_no);
    }

    @Override
    public Long findMyProfileNo(Long member_no) {
        return imageRepository.findMyProfileNo(member_no);
    }

    @Override
    public List<ProfileRes> findMyProfile(String email) {
        return repository.findMyProfile(email);
    }

    @Override
    public void lectureAddImage(String thum, Long id) {
//
//        Member member = em.find(Member.class, id);
//
//        try {
//            if (member.getThumb_path() != null) {
//                lectureUtil.deleteUtil("image", member.getThumb_path());
//            }
//        } finally {
//            member.setLectureThumb(thum);
//        }


//        imageRepository.deleteImage(id);
//        imageRepository.flush(); // jpa는 insert로 시작해서 delete로 끝나는데 이 코드로 delete 실행 후 일단 한 번 저장한다

        MemberProfileImage memberProfileImage = new MemberProfileImage();
        memberProfileImage.setMemberNo(id);
        memberProfileImage.setThumb_path(thum);

        imageRepository.save(memberProfileImage);
    }

//    @Override
//    public void deleteImage(Long member_no) throws Exception {
//        imageRepository.deleteImage(member_no);
//    }

    public void delete(Long profileNo) throws Exception {
        MemberProfileImage memberProfileImage = new MemberProfileImage();
        memberProfileImage.setProfileNo(profileNo);

        imageRepository.delete(memberProfileImage);
    }

}