package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

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
