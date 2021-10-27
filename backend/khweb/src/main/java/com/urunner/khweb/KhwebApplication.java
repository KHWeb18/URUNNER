package com.urunner.khweb;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.Role;
import com.urunner.khweb.service.member.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Properties;

@SpringBootApplication
public class KhwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhwebApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//@Bean public static JavaMailSender getJavaMailSender() { JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); mailSender.setHost("smtp.gmail.com"); mailSender.setPort(587); mailSender.setUsername("{{ 메일계정 아이디}}"); mailSender.setPassword("{{메일계정 비번}}"); Properties props = mailSender.getJavaMailProperties(); props.put("mail.transport.protocol", "smtp"); props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable", "true"); props.put("mail.debug", "true"); return mailSender; }

}
