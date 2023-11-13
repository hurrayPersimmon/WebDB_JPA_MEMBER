package com.ajs.demo.jpa.member.Controller;

import com.ajs.demo.jpa.member.Entity.Member;
import com.ajs.demo.jpa.member.ExceptionHandler.MemberApiException;
import com.ajs.demo.jpa.member.Entity.MemberApiStatus;
import com.ajs.demo.jpa.member.Repository.MemberRepository;
import com.ajs.demo.jpa.member.Response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;

import java.util.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Member API Controller", description="Member demo api 서비스 입니다.")
public class MemberApiController {
    private final MemberRepository memberRepository;

    //OAS 시스템. Operation
    @Operation(summary="전체 회원 조회", description="시스템에 등록된 모든 회원 정보를 요청 합니다.")
    @GetMapping("")
    public Iterable<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @ApiResponses({
            @ApiResponse(responseCode="200", description="처리 결과에 따라 API_ERROR 전달", content=@Content(schema=@Schema(implementation= MemberResponse.class))),
    })
    @GetMapping("{id}")
    public Member getMember(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberApiException(MemberApiStatus.MEMBER_NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void delMember(@PathVariable Long id, HttpServletRequest request) {
        memberRepository.deleteById(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member, BindingResult result) {
        List<String> msgs = new ArrayList<>();
        if(result.hasErrors()) {
            result.getAllErrors().stream().forEach(oe -> msgs.add(oe.getDefaultMessage()));
            throw new MemberApiException(MemberApiStatus.VALIDATION_ERROR,StringUtils.join(msgs,','));
//            return new ResponseEntity<>(member, HttpStatus.BAD_REQUEST);
            //response Entity로 상태코드 전송
        }
        memberRepository.save(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
//
//    // @WebTestClient 사용을 위한 flux 버전 형태
//    @PostMapping(value = "/flux")
//    public ResponseEntity<Member> addMemberFlux(@Valid @RequestBody Mono<Member> member) {
//        List<String> msgs = new ArrayList<>();
//        member.doOnError(ex -> {
//            log.error("error: {}", ex.getMessage());
//            throw new MemberApiException(MemberApiStatus.VALIDATION_ERROR,StringUtils.join(msgs,','));
//        });
//        memberRepository.save(member.block());
//        return new ResponseEntity<>(member.block(), HttpStatus.CREATED);
//    }


    @PutMapping("")
    public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member, BindingResult result) {
        List<String> msgs = new ArrayList<>();
        if(result.hasErrors()) {
            result.getAllErrors().stream().forEach(oe -> msgs.add(oe.getDefaultMessage()));
            throw new MemberApiException(MemberApiStatus.VALIDATION_ERROR,StringUtils.join(msgs,','));
        }

        // 멤버를 찾아 업데이트
        Member existingMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberApiException(MemberApiStatus.MEMBER_NOT_FOUND));

        // 필요한 업데이트 수행
        existingMember.setUsername(member.getUsername());
        existingMember.setEmail(member.getEmail());
        // 다른 필드들도 업데이트

        memberRepository.save(existingMember);
        return new ResponseEntity<>(existingMember, HttpStatus.OK);
    }

//    @PutMapping("")
//    public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member, BindingResult result) {
//        List<String> msgs = new ArrayList<>();
//        if(result.hasErrors()) {
//            result.getAllErrors().stream().forEach(oe -> msgs.add(oe.getDefaultMessage()));
//            throw new MemberApiException(MemberApiStatus.VALIDATION_ERROR,StringUtils.join(msgs,','));
//        }
//        memberRepository.save(member);
//        return new ResponseEntity<>(member, HttpStatus.OK);
//    }

    @ExceptionHandler
    private MemberResponse handleError(MemberApiException e, HttpServletRequest request) {
        return MemberResponse.builder()
                .path(request.getServletPath())
                .error(e.getStatus().getError())
                .message(e.getMessage())
                .build();
    }
}