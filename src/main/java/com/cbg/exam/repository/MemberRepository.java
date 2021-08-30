package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    public List findAll(){
        return em.createQuery("SELECT m FROM Member m")
                .getResultList();
    }

    public Optional<Member> findByLoginId(String loginId) {

        Optional<Member> findMember = em.createQuery("SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .setMaxResults(1)
                .getResultList()
                .stream().findFirst();
        return findMember;

    }
}

//@Repository
//public interface MemberRepository extends JpaRepository<Member, Long> {
//
//    Long save(Member member);
//
//    void delete(Member member);
//
//    static Long modify(Member member){
//        return member.getId();
//    }
//
//    Optional<Member> findById(Long id);
//
//    Optional<Member> findByLoginId(String loginId);
//}
