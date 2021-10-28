package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberProfileServiceImpl implements MemberProfileService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberProfileRepository repository;

    @Override
    public void register(MemberRes memberRes) throws Exception {

        Member memberEntity = new Member();
        System.out.println(memberEntity);

        repository.save(memberEntity);
    }

    @Override
    public void update(MemberRes memberRes) throws Exception {

        String name = memberRes.getName();
        String email = memberRes.getEmail();
        String introduce = memberRes.getIntroduce();

        System.out.println("Repository STEP(no password)");
        System.out.println("memberEntity.email : " + email);
        System.out.println("memberEntity.name : " + name);
        System.out.println("memberEntity.introduce : " + introduce);

        repository.update(name, email, introduce);
    }

    @Override
    public void update2(MemberRes memberRes) throws Exception {

        String name = memberRes.getName();
        String email = memberRes.getEmail();
        String password = passwordEncoder.encode(memberRes.getPassword());
        String introduce = memberRes.getIntroduce();

        System.out.println("Repository STEP(with password)");
        System.out.println("memberEntity.email : " + email);
        System.out.println("memberEntity.name : " + name);
        System.out.println("memberEntity.password : " + password);
        System.out.println("memberEntity.introduce : " + introduce);

        repository.update2(name, email, password, introduce);
    }

    @Override
    public String findMyIntroduce(MemberRes memberRes) throws Exception {
        return repository.findMyIntroduce(memberRes.getEmail());
    }
}