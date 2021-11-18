package com.urunner.khweb;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.PurchasedLecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.lecture.PurchasedLectureRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;



@SpringBootTest
class KhwebApplicationTests {

	@Autowired
	PurchasedLectureRepository purchasedLectureRepository;

	@Autowired
	MemberRepository memberRepository;


	@Autowired
	LectureRepository lectureRepository;
	@Test
	void contextLoads() {
		/*
		public Lecture(String writer, String title,
				String description, Long price, boolean inProgress, boolean discounted)

		Lecture lecture1 = new Lecture(null,"강의1",null,null,false,false);
		lectureRepository.save(lecture1);
		Lecture lecture2 = new Lecture(null,"강의2",null,null,false,false);
		lectureRepository.save(lecture2);
		Lecture lecture3 = new Lecture(null,"강의3",null,null,false,false);
		lectureRepository.save(lecture3);
		Lecture lecture4 = new Lecture(null,"강의4",null,null,false,false);
		lectureRepository.save(lecture4);
		Lecture lecture5 = new Lecture(null,"강의5",null,null,false,false);
		lectureRepository.save(lecture5);




		/*
		Member member = new Member();
		member.setEmail("injun0607@naver.com");
		memberRepository.save(member);

		List<String> lists = new ArrayList<>();
		lists.add("강의1");
		lists.add("강의2");
		lists.add("강의3");

		String email = "injun0607@naver.com";
		Member findMember = memberRepository.findByEmail(email);

		/*
		for(int i = 0; i<lists.size();i++) {
			String title = lists.get(i);
			System.out.println(title);
			System.out.println("memberNo:" + findMember.getMemberNo());
			if (lectureRepository.findByTitle(title) != null) {
				PurchasedLecture purchasedLecture = new PurchasedLecture();
				purchasedLecture.setTitle(title);
				findMember.addPurchasedLecture(purchasedLecture);
				System.out.println("멤버 저장합니다");
				memberRepository.save(findMember);
				//마이페이지 구매강의 접근시 purchasedLectureRepository.getTitle() 로 강의
				// 이름 불러온후 lectureRepository에서find로강의 불러오기

			}}

		System.out.println(findMember.getPurchasedLectureList().get(0).getTitle());
		//System.out.println(member.getPurchasedLectureList().get(1).getTitle());



/*
		Member member = new Member();
		member.setEmail("injun0607@naver.com");
		memberRepository.save(member);

		String title = "강의1";

		System.out.println(lectureRepository.findByTitle("강의1").getTitle());
		System.out.println(lectureRepository.findByTitle("강의2").getTitle());

		PurchasedLecture purchasedLecture = new PurchasedLecture();
		purchasedLecture.setTitle(title);
		member.addPurchasedLecture(purchasedLecture);
		memberRepository.save(member);

		System.out.println();

 */


		}

}
