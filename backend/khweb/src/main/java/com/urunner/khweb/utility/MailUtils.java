package com.urunner.khweb.utility;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {



    public static StringBuffer mailCertNumber(){
        StringBuffer result = new StringBuffer();
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int cLength = c.length();
        for (int i = 0; i< 5;i++){
            result.append(c.charAt((int)(Math.random()*cLength)));
        }
        return result;
    }

    public static void sendMail(String email,String certCode) throws Exception {

        String host = "smtp.naver.com"; //구글계정으로 할시("smtp.gmail.com")
        //관리자계정으로 변환부분
        String user = "injun0607@naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "비밀번호입력!";   // 패스워드
        String checkCode = certCode; // 인증코드
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


            message.setText("테스트 인증코드 : " + checkCode );    //메일 내용을 입력

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
