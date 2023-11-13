package com.ajs.demo.jpa.member.Repository;

import com.ajs.demo.jpa.member.Entity.Member;
import com.ajs.demo.jpa.member.Entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {

    void deleteById(Long id);

    Optional<Object> findByMemberId(Long memberId);
}
