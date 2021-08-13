package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void saveMember(){
        Member member1  = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);
    }

}
