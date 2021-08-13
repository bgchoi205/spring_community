package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

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

    @Test
    public void findMemberById(){
        Long id = 2L;

        Optional<Member> findedMember = memberRepository.findById(id);

        if(findedMember.isPresent()){
            Member member = findedMember.get();
            System.out.println(member);
        }
    }

    @Test
    public void updateMember(){
        Member member = Member.builder()
                .id(3L)
                .name("Hello")
                .build();
        memberRepository.save(member);
    }

    @Test
    public void deleteMember(){
        Long id = 4L;

        memberRepository.deleteById(id);
    }

}
