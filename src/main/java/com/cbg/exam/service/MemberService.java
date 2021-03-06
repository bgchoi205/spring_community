package com.cbg.exam.service;

import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.domain.Role;
import com.cbg.exam.repository.MemberRepository;
import com.cbg.exam.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Member member){
        memberRepository.save(member);
    }

    @Transactional
    public void delete(Member member){
        memberRepository.delete(member);
    }

    public List findAll() {
        return memberRepository.findAll();
    }

    public Member findByLoginId(String loginId) {
        Member findMember = memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원-findByLoginId"));

        return findMember;
    }


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepository.findByLoginId(loginId);
        Member logonMember = memberEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (logonMember.getAuthLevel() == 7) {  // 로그인 시 권한설정, domain패키지에 Role enum 생성함.

            // authLevel이 7일 때 관리자 권한을 부여.
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
            authorities.add(new SimpleGrantedAuthority(Role.MANAGER.getValue()));
        } else {

            // authLevel이 7 이외일때 일반 회원으로 권한 부여
            authorities.add(new SimpleGrantedAuthority(Role.MANAGER.getValue()));
        }

        return new CustomUserDetails(logonMember.getId(), logonMember.getLoginId(), logonMember.getLoginPw(), logonMember.getName(), logonMember.getNickname()
        , logonMember.getEmail(), authorities);
    }

    // 회원 유무 확인
    private boolean isMemberEmpty(Long memberId) {
        return memberRepository.findById(memberId).isEmpty();
    }

    // 회원계정 삭제
    @Transactional
    public boolean apiDeleteMember(Long memberId) {
        if( isMemberEmpty(memberId) ){
            return false;
        }

        memberRepository.deleteById(memberId);
        return true;
    }

    // 로그인아이디 중복 확인
    public boolean apiCheckLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).isEmpty();
    }

    // 닉네임 중복 확인
    public boolean apiCheckNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isEmpty();
    }
}
