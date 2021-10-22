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


	@Bean
	CommandLineRunner run(MemberService userService) {
		return test -> {

			MemberRes test1 = new MemberRes();
			test1.setEmail("kim");
			test1.setPassword("1234");
			test1.setName("test");


			userService.registerMember(test1);



//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveMember(new Member("kim", "1234", "kim"));
//			userService.saveMember(new Member("Lee", "1234", "Lee"));
//			userService.saveMember(new Member("Park", "1234", "Park"));
//			userService.saveMember(new Member("Mama", "1234", "Mama"));
//
//			userService.addRoleToMember("Lee","ROLE_MANAGER");
//			userService.addRoleToMember("Lee","ROLE_ADMIN");
//			userService.addRoleToMember("Park","ROLE_SUPER_ADMIN");
//			userService.addRoleToMember("Mama","ROLE_USER");
//			userService.addRoleToMember("kim","ROLE_USER");

		};
	}

}
