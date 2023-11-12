package com.ajs.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByStudentId(Long id);
    List<Score> findAllByScoreIsGreaterThanAndScoreIsLessThan(int min, int max);
    // 앞보다는 크고 뒤보다는 작은 범위 네임드 쿼리. - 네임드 쿼리 권장.

    // 직접 쿼리문을 줘서 매핑되도록 하기. native sql
    @Query("select new com.ajs.demo.jpa.ScoreReport(s.student.name, sum(s.score), avg(s.score)) from Score s group by s.student order by avg(s.score) desc")
    List<ScoreReport> generateScoreReport();
    // 두 테이블 간의 정보를 뽑아낸 새로운 클래스 타입 ScoreReport로 정의 및 리턴

    // jpql 자바 객체 사용. 엔티티명 사용.
    // 프로젝션, 매핑되는 인터페이스의 필드명과 같도록 as 처리해야 함.
    @Query(value = "select st.NAME, sum(s.SCORE) as total, avg(s.SCORE) as avg, row_number() over () as rank from Score s, STUDENT st where s.STUDENT_ID = st.ID group by s.STUDENT_ID", nativeQuery = true)
    List<ScoreReport2> generateScoreReport2();
    //interface 정의. 클래스타입으로 알아서 리턴.
}