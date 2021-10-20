package com.urunner.khweb.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "member") @Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;
    private String email;
    private String name;
    private String password;

    public void setEmail() {
        this.email = email;
    }

    public void setName() {
        this.name = name;
    }

    public void setPassword() {
        this.password = password;
    }

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}