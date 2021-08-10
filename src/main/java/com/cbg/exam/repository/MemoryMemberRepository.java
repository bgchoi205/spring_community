package com.cbg.exam.repository;

import com.cbg.exam.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    public static Long memberId = 0L;
    public static Map<Long, Member> members = new HashMap<>();

    @Override
    public Member save(Member member) {
        Long id = ++memberId;
        member.setId(id);
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return members.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    public void clearMembers(){
        members.clear();
    }
}
