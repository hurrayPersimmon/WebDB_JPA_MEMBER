package com.ajs.demo.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@ToString
public class ScoreReport {
    private String name;
    private long totalScore;
    private double average;
}
