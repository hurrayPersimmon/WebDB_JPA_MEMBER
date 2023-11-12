package com.ajs.demo.jpa.member;

//import com.samskivert.mustache.Mustache;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    // OAS 스키마 정보 제공
    @Schema(description="고유 id", nullable = true, example="11234")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "이름은 반드시 입력 해야 합니다!!")
    private String name;

    @Email(message = "이메일 형식을 따라야 합니다!!")
    @NotBlank(message = "이메일은 반드시 입력 해야 합니다!!")
    private String email;

    public Member(String name , String email) {
        this.name = name;
        this.email = email;
    }

//    public Mustache.Lambda convert() {
//        return (frag, out) -> out.write("<h2>"+frag.execute()+"</h2>");
//    }
}
