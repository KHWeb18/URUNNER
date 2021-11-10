package com.urunner.khweb.entity.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "study_member")
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(length = 100, nullable = false)
    private Long boardNo;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String nickname;

    @Column(length = 200, nullable = false)
    private String introduce;

    @CreationTimestamp
    private Date regDate;

    public StudyMember(Long boardNo, String email, String nickname, String introduce) {
        this.boardNo = boardNo;
        this.email = email;
        this.nickname = nickname;
        this.introduce = introduce;
    }
//    insert into study_member (board_no, email, introduce, name) value (3, "start132@naver.com", "ㅎㅇㅎㅇ", "야옹이")
}