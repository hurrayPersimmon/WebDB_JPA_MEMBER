package com.ajs.demo.jpa.member.Entity;

//import com.samskivert.mustache.Mustache;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Member {
    // OAS 스키마 정보 제공
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private Long id;

    @NotBlank(message = "이름은 반드시 입력 해야 합니다!!")
    private String username;

    @Email(message = "이메일 형식을 따라야 합니다!!")
    @NotBlank(message = "이메일은 반드시 입력 해야 합니다!!")
    private String email;

    @ToString.Exclude
    @OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE)
    private MemberInfo memberInfo;

    public Member(String username , String email) {
        this.username = username;
        this.email = email;
    }

//    public Mustache.Lambda convert() {
//        return (frag, out) -> out.write("<h2>"+frag.execute()+"</h2>");
//    }
}
