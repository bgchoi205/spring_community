package com.cbg.exam.service;

import com.cbg.exam.domain.Member;
import com.cbg.exam.domain.Role;
import com.cbg.exam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long save(Member member){

        return memberRepository.save(member);
    }

    @Transactional
    public Long delete(Member member){
        return memberRepository.delete(member);
    }

    @Transactional
    public Long modify(Member member){
        return memberRepository.modify(member);
    }

    @Transactional
    public Member findById(Long id){
        return memberRepository.findById(id);
    }

    @Transactional
    public List findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public Optional<Member> findMemberByLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        findMember.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원-findByLoginId"));

        return findMember;
    }


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepository.findByLoginId(loginId);
        Member memberEntity = memberEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("aa").equals(loginId)) {  // 로그인 시 권한설정, domain패키지에 Role enum 생성함.

            // 로그인 아이디가 aa일 때 관리자 권한을 부여.
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {

            // 로그인 아이디가 aa 이외일때 일반 회원으로 권한 부여
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        // spring security에서 제공하는 UserDetails를 구현한 User를 반환(org.springframework.security.core.userdetails.User )
        // 반환하는 정보는 로그인아이디, 로그인비밀번호, 권한리스트이다.
        return new User(memberEntity.getLoginId(), memberEntity.getLoginPw(), authorities);
    }
}
