package com.urunner.khweb.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    private String email;
    private String name;
    private String password;
    private String introduce;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date upDate;


    public Member(String email, String password, String name, String introduce) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.introduce = introduce;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_no")
    private Collection<Role> roles = new ArrayList<>();

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


}