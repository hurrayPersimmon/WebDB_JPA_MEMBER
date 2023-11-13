package com.ajs.demo.jpa.member.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberApiStatus {
    VALIDATION_ERROR("VALIDATION_ERROR", "검증 오류 발생"),
    MEMBER_NOT_FOUND("API_ERROR", "회원을 찾을 수 없습니다!!");

    private final String error;
    private final String message;
}
