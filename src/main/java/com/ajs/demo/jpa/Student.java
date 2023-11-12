package com.ajs.demo.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Score> scores = new ArrayList<>();
    // 스코어 리파지토리 없이 스튜던트로 save해도 스코어에 저장된다?

    public Student(String name) {
        this.name = name;
    }
}
