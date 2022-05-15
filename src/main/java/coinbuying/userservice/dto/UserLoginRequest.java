package coinbuying.userservice.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    private String email; // 회원 이메일

    private String password; // 회원 비밀번호

}
