package com.urunner.khweb.repository.member;

import com.urunner.khweb.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);

    public Member findByNickname(String nickname);

    public void deleteByEmail(String email);

    @Query("select m from Member m join fetch m.purchasedLectureList p where p.memberNo = :memberNo and p.lecture_id = :lectureId")
    public Optional<Member> getIsPurchased(@Param("memberNo") Long memberNo, @Param("lectureId") Long lectureId);

}
