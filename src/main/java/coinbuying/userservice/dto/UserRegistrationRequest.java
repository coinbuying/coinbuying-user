package coinbuying.userservice.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    private String name; // 회원 이름

    private String email; // 회원 이메일

    private String password; // 회원 비밀번호

}
