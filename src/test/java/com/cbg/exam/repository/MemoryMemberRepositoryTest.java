package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearMembers();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("user1");
        repository.save(member);

        Member findMember = repository.findById(member.getId()).get();
        assertThat(findMember.getName()).isEqualTo("user1");
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("user1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("user2");
        repository.save(member2);

        Member findMemberByName = repository.findByName("user2").get();
        assertThat(findMemberByName.getName()).isEqualTo("user2");
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("user1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("user2");
        repository.save(member2);

        List mList = repository.findAll();
        assertThat(mList.size()).isEqualTo(2);
    }
}
