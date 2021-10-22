package com.urunner.khweb.repository.member;

import com.urunner.khweb.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemberProfileRepository extends JpaRepository<Member, Long> {


    @Transactional
    @Modifying
    @Query("update Member u set u.name = ?1 where u.email = ?2")
//    @Query(value = "update member set name = ?1 where email = ?2", nativeQuery = true)
    void update(String name, String email);

    @Transactional
    @Modifying
    @Query("update Member u set u.name = ?1, u.password = ?3 where u.email = ?2")
    void update2(String name, String email, String password);
}