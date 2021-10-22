package com.urunner.khweb;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.Role;
import com.urunner.khweb.service.member.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class KhwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhwebApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
