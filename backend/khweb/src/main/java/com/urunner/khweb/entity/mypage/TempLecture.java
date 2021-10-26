package com.urunner.khweb.entity.mypage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TempLecture {
    //테스트를 위한 임시강의 입니다. 나중에 Lecture Entity생성후 삭제예정 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lect_no")
    private Long lectNo;

    private String title;
    private String content;
    private Date clickDate;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;


}
