package com.ajs.demo.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String subject;
    public int score;

    @ManyToOne
    @JoinColumn(name = "student_id")
    public Student student;

    public Score(String subject, int score, Student student) {
        this.subject = subject;
        this.score = score;
        this.student = student;
    }
}
