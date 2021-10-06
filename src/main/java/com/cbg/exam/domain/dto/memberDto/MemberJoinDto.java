package com.cbg.exam.domain.dto.memberDto;

import com.cbg.exam.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class MemberJoinDto {
    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Builder
    public MemberJoinDto(String loginId, String loginPw, String name, String nickname, String email){
        this.loginId = loginId;
        this.loginPw = passwordEncoder.encode(loginPw);
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public Member toEntity(){
        return Member.builder()
                .loginId(loginId)
                .loginPw(loginPw)
                .name(name)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
