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
        String password = memberRes.getPassword();

        System.out.println("Repository STEP(no password)");
        System.out.println("memberEntity.email : " + email);
        System.out.println("memberEntity.name : " + name);
        System.out.println("memberEntity.password : " + password);

        repository.update(name, email);
    }

    @Override
    public void update2(MemberRes memberRes) throws Exception {

        String name = memberRes.getName();
        String email = memberRes.getEmail();
        String password = passwordEncoder.encode(memberRes.getPassword());

        System.out.println("Repository STEP(with password)");
        System.out.println("memberEntity.email : " + email);
        System.out.println("memberEntity.name : " + name);
        System.out.println("memberEntity.password : " + password);

        repository.update2(name, email, password);
    }
}