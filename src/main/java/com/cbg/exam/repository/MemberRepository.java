package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Member member){
        em.persist(member);
    }

    @Transactional
    public Member findById(int id){
        return em.find(Member.class, id);
    }

    @Transactional
    public List findAll(){
        return em.createQuery("SELECT m FROM Member m")
                .getResultList();
    }
}

//public interface MemberRepository extends JpaRepository<Member, Long> {
//
//}
