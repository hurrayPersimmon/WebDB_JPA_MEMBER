package com.ajs.demo.jpa.member;

//import com.samskivert.mustache.Mustache;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInfo {
    // OAS 스키마 정보 제공
    @Schema(description="고유 id", nullable = true, example="11234")
    private Integer id;
    @NotBlank(message = "이름은 반드시 입력 해야 합니다!!")
    private String name;

    @Email(message = "이메일 형식을 따라야 합니다!!")
    @NotBlank(message = "이메일은 반드시 입력 해야 합니다!!")
    private String email;

    public MemberInfo(String name , String email) {
        this.name = name;
        this.email = email;
    }

//    public Mustache.Lambda convert() {
//        return (frag, out) -> out.write("<h2>"+frag.execute()+"</h2>");
//    }
}
