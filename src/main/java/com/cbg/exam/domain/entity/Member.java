package com.cbg.exam.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="member")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login_id")
    private String loginId;

    @Column(name="login_pw")
    private String loginPw;

    @Column(name="name")
    private String name;

    @Column(name="nickname")
    private String nickname;

    @Column(name="email")
    private String email;

    @Column(name="auth_level")
    private int authLevel;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Article> articles;

    @Builder
    public Member(String loginId, String loginPw, String name, String nickname, String email){
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.authLevel = 3;
    }

    public void changeAuth(int authLevel){
        this.authLevel = authLevel;
    }

}
