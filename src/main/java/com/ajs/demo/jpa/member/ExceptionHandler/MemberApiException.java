package com.ajs.demo.jpa.member.ExceptionHandler;

import com.ajs.demo.jpa.member.Entity.MemberApiStatus;
import lombok.Getter;

@Getter
public class MemberApiException extends RuntimeException{
    private MemberApiStatus status;
    public MemberApiException(MemberApiStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public MemberApiException(MemberApiStatus status, String message) {
        super(message);
        this.status = status;
    }
}
