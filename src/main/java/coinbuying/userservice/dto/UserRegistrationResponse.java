package coinbuying.userservice.dto;

import coinbuying.userservice.dto.common.SuccessDto;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse extends SuccessDto {

    private int userId;
}
