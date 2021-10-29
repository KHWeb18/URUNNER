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

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService{


    @Autowired
    private MemberProfileRepository repository;

    @Override
    public void sendMail(String email) throws Exception {
        String host = "smtp.naver.com"; //구글계정으로 할시("smtp.gmail.com")
        //관리자계정으로 변환부분
        String user = "injun0607@naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "password";   // 패스워드


        // SMTP 서버 정보를 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", 587); //구글계정포트(465)
        prop.put("mail.smtp.auth", "true");

        /*구글이메일 설정시 해제
        prop.put("mail.smtp.ssl.enable", "true"); prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        */

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));


            message.setSubject("테스트메일입니당"); //메일 제목을 입력


            message.setText("테스트내용이에요");    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



}
