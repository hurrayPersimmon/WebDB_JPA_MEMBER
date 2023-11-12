package com.ajs.demo.jpa.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

//메세지 빌더
@Builder
@Getter
public class MemberResponse {
    //스웨거에 업로드하기 위함.
    @Schema(description="요청 경로",example="/member/{id}")
    private final String path;
    @Schema(description="에러 유형",example="API_ERROR, VALIDATION_ERROR")
    private final String error;
    @Schema(description="에러 메시지",example="MemberApiStatus에 정의된 상태/에러 메시지")
    private final String message;
}
