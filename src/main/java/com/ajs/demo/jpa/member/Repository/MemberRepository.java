package com.ajs.demo.jpa.member.Repository;

import com.ajs.demo.jpa.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByUsername(String username);

    void deleteById(Long id);
}
