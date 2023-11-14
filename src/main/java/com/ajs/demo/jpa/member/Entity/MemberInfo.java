package com.ajs.demo.jpa.member.Entity;

//import com.samskivert.mustache.Mustache;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class MemberInfo {
    // OAS 스키마 정보 제공
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence_generator")
//    @SequenceGenerator(name = "member_sequence_generator", sequenceName = "member_sequence", allocationSize = 1)
    private Long id;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Jobs job;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

    public MemberInfo(String phoneNumber , Jobs job) {
        this.phoneNumber = phoneNumber;
        this.job = job;
    }

//    public Mustache.Lambda convert() {
//        return (frag, out) -> out.write("<h2>"+frag.execute()+"</h2>");
//    }
}
