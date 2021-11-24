package com.urunner.khweb.entity.lecture;


import com.urunner.khweb.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class PurchasedLecture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purchased_lecture_id")
    private Long purchasedLectureId;

    private String title;

    private Long lecture_id;

    private String orederId;

    @Column(name = "member_no")
    private Long memberNo;

    @CreationTimestamp
    private Date regDate;

}
