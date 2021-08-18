package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository{

    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Long delete(Member member){
        em.remove(member);
        return member.getId();
    }

    public Long modify(Member member){
        return save(member);
    }

    public Member findById(int id){
        return em.find(Member.class, id);
    }

    public List findAll(){
        return em.createQuery("SELECT m FROM Member m")
                .getResultList();
    }
}

//public interface MemberRepository extends JpaRepository<Member, Long> {
//
//}
