package com.cbg.exam.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="member")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login_id", nullable = false)
    private String loginId;

    @Column(name="login_pw", nullable = false)
    private String loginPw;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name="email", nullable = false)
    private String email;



    @Builder
    public Member(String loginId, String loginPw, String name, String nickname, String email){
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

}
