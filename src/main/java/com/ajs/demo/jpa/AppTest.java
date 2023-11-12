package com.ajs.demo.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@Transactional
//트랜잭션 아주 중요하지 암.
public class AppTest implements ApplicationRunner {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1. Student 엔티티로 부터 과목별 점수 출력
        System.out.println("### 1. Student 엔티티로 부터 과목별 점수 출력 ###");
        for(Student s : studentRepository.findAll()) {
            System.out.println(s.getName());
            for(Score sc : s.getScores()) {
                System.out.println(sc.getSubject() + " : " + sc.getScore());
            }
        }
        System.out.println("=====================================");

        // 2. Score 엔티티로 부터 점수 출력
        System.out.println("### 2. Score 엔티티로 부터 점수 출력 ###");
        for(Student s : studentRepository.findAll()) {
            System.out.println(s.getName());
            for(Score sc : scoreRepository.findAllByStudentId(s.getId())) {
                System.out.println(sc.getSubject() + " : " + sc.getScore());
            }
        }
        System.out.println("=====================================");

        // 3. score 엔티티에서 학생 이름과 점수 출력
        System.out.println("### 3. score 엔티티에서 학생 이름과 점수 출력 ###");
        for(Score sc : scoreRepository.findAll()) {
            System.out.println(sc.getStudent().getName());
            System.out.println(sc.getSubject() + " : " + sc.getScore());
        }
        System.out.println("=====================================");

        // 4. Like 검색
        System.out.println("### 4. Like 검색 ###");
        studentRepository.findByNameLike("%길%").forEach(System.out::println);
        System.out.println("=====================================");

        // 5. NamedQuery 조건 검색
        System.out.println("### 5. NamedQuery 조건 검색 ###");
        scoreRepository.findAllByScoreIsGreaterThanAndScoreIsLessThan(80, 100).forEach(System.out::println);
        System.out.println("=====================================");

        // 6. JPQL 성적 리포트
        System.out.println("### 6. JPQL 성적 리포트(총점, 평균, 정렬, group by) ###");
        scoreRepository.generateScoreReport().forEach(System.out::println);
        System.out.println("=====================================");

        // 7. Projection 성적 리포트
        System.out.println("### 7. Projection 성적 리포트(등수, 총점, 평균, 정렬) ###");
        scoreRepository.generateScoreReport2().forEach(
                report -> System.out.printf("등수: %d, %s, 총점: %d, 평균: %d\n",
                        report.getRank(), report.getName(), report.getTotal(), report.getAvg()
                )
        );

        // 8. 페이징 테스트
        System.out.println("### 8. 페이징 테스트 ###");

        // Student 테이블에 100명의 학생 추가, 기초 데이터에서 2명 들어가 있음.
        for (int i = 3; i <= 100; i++) {
            studentRepository.save(new Student("학생" + i));
        }

        Page<Student> students = studentRepository.findAllByOrderById(PageRequest.of(2, 20));
        students.forEach(
                student -> System.out.printf("학번: %d, 이름: %s\n", student.getId(), student.getName())
        );
        // 샘플 데이터 롤백을 위한 예외 발생. -> 트랜잭션.
        throw new RuntimeException("테스트 종료!!");
    }
}
