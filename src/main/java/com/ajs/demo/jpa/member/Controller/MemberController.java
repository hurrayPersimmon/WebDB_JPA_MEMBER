package com.ajs.demo.jpa.member.Controller;

import com.ajs.demo.jpa.member.Entity.Member;
import com.ajs.demo.jpa.member.Entity.MemberInfo;
import com.ajs.demo.jpa.member.Repository.MemberInfoRepository;
import com.ajs.demo.jpa.member.Repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;

    // 리턴 - void, 요청 경로(디렉토리 포함)와 동일한 뷰 템플릿 호출
    // WebConfig -> addViewControllers() 에서 처리할 수도 있음
    @GetMapping("signup_form")
    public void getMemberForm() {
        log.debug("getMemberForm() 호출됨!!");
    }

    @GetMapping("list")
    public String getAll(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "member/member_list";
    }

    // 인자 - query string
    @GetMapping("get")
    public String getMember1(@RequestParam Long id, Model model) {
        model.addAttribute("member", memberRepository.findById(id).orElseThrow());
        return "member/member_info";
    }

    // 인자 - path variable
    @GetMapping("{id}")
    public String getMember2(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberRepository.findById(id).orElseThrow());
        model.addAttribute("memberInfo", memberInfoRepository.findByMemberId(id));
        return "member/member_info";
    }

    // 삭제
    @GetMapping("del/{id}")
    public String delMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return "redirect:/member/list";
    }

    // 인자 - model attribute, Errors 를 사용해도 됨.
    @PostMapping("signup")
    public String signUp(@ModelAttribute @Valid Member m, BindingResult errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors.getAllErrors());
            return "member/signup_form";
        }
        else {
            // @ModelAttribute 사용시 해당 model attribute 가 자동으로 model 에 추가됨.
//            MemberInfo memberInfo = new MemberInfo();
//            m.setMemberInfo(memberInfo);
//            memberInfoRepository.save(memberInfo);
            memberRepository.save(m);

            //return "member/member_info";
            return "redirect:/member/list";
        }
    }

    // 메서드에 사용. 다른 요청 수행전 먼저 실행 됨.
    @ModelAttribute
    public void setAttribute(Model model) {
        model.addAttribute("memberCnt", ((Collection<?>) memberRepository.findAll()).size());
        //model.addAttribute("memberCnt", StreamSupport.stream(memberRepo.findAll().spliterator(),false).count());
    }

    // 기본적으로 예외 발생시 error 페이지로 이동하지만 특정 예외를 지정해 추가적인 메시지등을 포함해 별도 처리페이지로 보내는 것도 가능.
//    @ExceptionHandler
//    public String handleException(Exception e, Model model) {
//        model.addAttribute("exception", e);
//        return "error";
//    }
}
