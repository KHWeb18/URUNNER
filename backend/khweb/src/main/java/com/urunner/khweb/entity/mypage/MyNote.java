package com.urunner.khweb.entity.mypage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class MyNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_no")
    private Long noteNo;

    private String title;
    private String content;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;




}
