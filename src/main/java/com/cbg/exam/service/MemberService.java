package com.cbg.exam.service;

import com.cbg.exam.domain.Member;
import com.cbg.exam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void save(Member member){
        memberRepository.save(member);
    }

    public Member findById(int id){
        return memberRepository.findById(id);
    }

    public List findAll() {
        return memberRepository.findAll();
    }

//    public List findAll() {
//        return memberRepository.findAll();
//    }
}
