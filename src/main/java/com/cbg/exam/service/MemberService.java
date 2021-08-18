package com.cbg.exam.service;

import com.cbg.exam.domain.Member;
import com.cbg.exam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

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
    public Member findById(int id){
        return memberRepository.findById(id);
    }

    @Transactional
    public List findAll() {
        return memberRepository.findAll();
    }

}
