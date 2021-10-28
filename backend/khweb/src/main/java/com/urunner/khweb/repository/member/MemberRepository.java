package com.urunner.khweb.repository.member;

import com.urunner.khweb.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);

    public Member findByName(String username);

    public void deleteByEmail(String email);
}
