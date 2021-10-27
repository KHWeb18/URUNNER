package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MailDto;
import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberProfileRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberProfileRepository repository;

    @Autowired
    MemberRepository memberRepository;

    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "wsk0307@gmail.com";

    public MailDto createMailAndChangePassword(String email, String name){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(name+"님의 URUNNER 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. URUNNER 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        return dto;
    }

    public void updatePassword(MemberRes memberRes) throws Exception{
        String name = memberRes.getName();
        String email = memberRes.getEmail();
        String password = passwordEncoder.encode(memberRes.getPassword());

        Member member = memberRepository.findByEmail(email);
        repository.updatePassword(password);
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailServiceImpl.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}
