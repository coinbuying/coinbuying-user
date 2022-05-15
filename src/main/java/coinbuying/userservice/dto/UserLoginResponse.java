package coinbuying.userservice.dto;

import coinbuying.userservice.dto.common.SuccessDto;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse extends SuccessDto {
    private int memberId; // 회원 고유번호

    private String accessToken;

    private String refreshToken;
}
