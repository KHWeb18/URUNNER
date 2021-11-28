package com.urunner.khweb;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.PurchasedLecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.lecture.PurchasedLectureRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;



@SpringBootTest
class KhwebApplicationTests {

	@Autowired
	MemberService memberService;

	public static StringBuffer mailCertNumber(){
		StringBuffer result = new StringBuffer();
		String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int cLength = c.length();
		for (int i = 0; i< 5;i++){
			result.append(c.charAt((int)(Math.random()*cLength)));
		}
		return result;
	}

	@Test
	void contextLoads() {


	}

}
