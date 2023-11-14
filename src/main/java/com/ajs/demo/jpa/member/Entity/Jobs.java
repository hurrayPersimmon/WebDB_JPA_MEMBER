package com.ajs.demo.jpa.member.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Jobs {
    STUDENT("학생"),
    PROFESSOR("교수"),
    NONE("무직");

    private final String name; // ENUM 한글 활용
    // String이 아니더라도 function이 들어갈 수 있다.
    // ex) if, switch
}
