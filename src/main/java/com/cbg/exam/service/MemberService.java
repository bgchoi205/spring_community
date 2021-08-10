package com.cbg.exam.service;

import com.cbg.exam.domain.Member;
import com.cbg.exam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long save(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        return memberRepository.save(member).getId();

    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
