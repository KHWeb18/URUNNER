package com.urunner.khweb.repository.member;

import com.urunner.khweb.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberProfileRepository extends JpaRepository<Member, Long> {


    @Transactional
    @Modifying
    @Query("update Member u set u.name = ?1, u.introduce = ?3 where u.email = ?2")
//    @Query(value = "update member set name = ?1 where email = ?2", nativeQuery = true)
    void update(String name, String email, String introduce);

    @Transactional
    @Modifying
    @Query("update Member u set u.name = ?1, u.password = ?3, u.introduce = ?4 where u.email = ?2")
    void update2(String name, String email, String password, String introduce);

    @Transactional
    @Modifying
    @Query("update Member u set u.password = ?3 where u.email = ?2")
    void updatePassword(String password);

//    @Transactional
//    @Modifying
//    @Query("select u.introduce from Member u where u.email = ?1")
//    List<Member> findbyintroduce(String email);

    @Query(value = "select introduce from member where email = :email", nativeQuery = true)
    String findMyIntroduce(String email);
}