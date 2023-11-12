package com.ajs.demo.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {
    // 생성 sql은 containing, like 같음 but containing은 직접 % 넣지 않아도 됨.
    List<Student> findByNameContaining(String name);
    // DB like, 포함하고 있는 내용 전부 반환
    List<Student> findByNameLike(String name);
    // parameter에 % 들어가야 함.
    Page<Student> findAll(Pageable pageable);
    // page단위로 구성. page 옵션
    Page<Student> findAllByOrderById(Pageable pageable);
    // 정렬 오름차순. 역순으로 가지고가고 싶으면 IdDescending 하면 된다네
}