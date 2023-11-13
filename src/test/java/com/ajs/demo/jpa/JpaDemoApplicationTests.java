//package com.ajs.demo.jpa;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.system.OutputCaptureExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class JpaDemoApplicationTests {
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private ScoreRepository scoreRepository;
//
//    @BeforeAll
//    static void setUp(@Autowired StudentRepository studentRepository, @Autowired ScoreRepository scoreRepository) {
//        Student student1 = new Student("김길동");
//        Student student2 = new Student("김길순");
//        studentRepository.save(student1);
//        studentRepository.save(student2);
//
//        // student 리파지토리로 저장
////        student1.getScores().add(new Score("수학", 100, student1));
////        student1.getScores().add(new Score("국어", 95, student1));
////        student2.getScores().add(new Score("수학", 82, student2));
////        student2.getScores().add(new Score("영어", 70, student2));
//
//        // score 리파지토리로 저장
//        scoreRepository.save(new Score("수학", 100, student1));
//        scoreRepository.save(new Score("국어", 95, student1));
//        scoreRepository.save(new Score("수학", 82, student2));
//        scoreRepository.save(new Score("영어", 70, student2));
//    }
//
//    @Test
//    @DisplayName("Like 검색 테스트")
//    void likeTest() {
//        assertThat(studentRepository.findByNameContaining("길").size()).isEqualTo(2);
//        studentRepository.findByNameLike("%길%").forEach(System.out::println);
//    }
//
//    @Test
//    @DisplayName("조건 검색 테스트")
//    void conditionTest() {
//        assertThat(scoreRepository.findAllByScoreIsGreaterThanAndScoreIsLessThan(80, 100).size()).isEqualTo(2);
//    }
//
//    @Test
//    @DisplayName("성적 리포트 테스트")
//    void jpqlTest() {
//        assertThat(scoreRepository.generateScoreReport().size()).isEqualTo(2);
//        scoreRepository.generateScoreReport().forEach(System.out::println);
//    }
//
//    @Test
//    @DisplayName("성적 리포트 테스트")
//    void nativeTest() {
//        assertThat(scoreRepository.generateScoreReport2().size()).isEqualTo(2);
//        scoreRepository.generateScoreReport2().forEach(
//                report -> System.out.printf("등수: %d, %s, 총점: %d, 평균: %d\n",
//                        report.getRank(), report.getName(), report.getTotal(), report.getAvg()
//                )
//        );
//    }
//
//    @Test
//    @DisplayName("페이징 테스트")
//    void pagingTest() {
//        // Student 테이블에 100명의 학생 추가, 기초 데이터에서 2명 들어가 있음.
//        for (int i = 3; i <= 100; i++) {
//            studentRepository.save(new Student("학생" + i));
//        }
//        //Page<Student> students = studentRepository.findAll(PageRequest.of(2, 20));
//        Page<Student> students = studentRepository.findAllByOrderById(PageRequest.of(2, 20));
//        assertThat(students.getTotalElements()).isEqualTo(100);
//        assertThat(students.getTotalPages()).isEqualTo(5);
//        students.forEach(
//                student -> System.out.printf("학번: %d, 이름: %s\n", student.getId(), student.getName())
//        );
//    }
//}
