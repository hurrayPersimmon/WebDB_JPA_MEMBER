package com.ajs.demo.jpa.test;

import com.mvc.demo.member.Member;
import com.mvc.demo.member.MemberRepository;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AppConfig {
    @Bean
    public MemberRepository initMember() {
        MemberRepository memberRepository = new MemberRepository() {
            @Override
            public void init() {
                memberMap.put(110121,new Member(110121,"김사랑", "kim@test.com"));
                memberMap.put(210187,new Member(210187,"홍길동", "hong@test.com"));
                memberMap.put(675342,new Member(675342,"아무개", "anon@test.com"));
            }

            @Override
            public Optional<Member> findById(Integer id) {
                return Optional.ofNullable(memberMap.get(id));
            }

            @Override
            public Iterable<Member> findAll() {
                return memberMap.values();
            }

            @Override
            public Optional<Member> delete(Integer id) {
                return Optional.ofNullable(memberMap.remove(id));
            }

            @Override
            public Member save(Member member) {
                member.setId((int)(Math.random() * 89999) + 10000);
                memberMap.put(member.getId(), member);
                return member;
            }

            @Override
            public void deleteById(Integer id) {
                // 에러 테스트 샘플 -> 에러는 공통 에러페이지에서 처리됨.
                if(!memberMap.containsKey(id)) {
                    throw new RuntimeException("존재하지 않는 회원입니다.");
                }
                memberMap.remove(id);
            }
        };
        memberRepository.init();
        return memberRepository;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring Rest DEMO API")
                        .description("Spring rest member api demo application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://test.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring demo extra information")
                        .url("https://test.com"));
    }
}
