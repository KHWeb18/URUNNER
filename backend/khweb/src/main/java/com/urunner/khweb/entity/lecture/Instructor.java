package com.urunner.khweb.entity.lecture;


import com.urunner.khweb.entity.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor @Getter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String tags;


    @Builder
    public Instructor(String description, String tags) {
        this.description = description;
        this.tags = tags;
    }

    @OneToOne
    @JoinColumn(name = "id")
    Role role;

    public void setRole(Role role) {
        this.role = role;
    }
}
