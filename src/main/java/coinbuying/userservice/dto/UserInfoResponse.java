package coinbuying.userservice.dto;

import coinbuying.userservice.entity.User;
import coinbuying.userservice.entity.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoResponse {
    private String name;
    private String email;
    private UserType userType;
    private String bithumbToken;
    private String upbitToken;

    @Builder
    public UserInfoResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        this.bithumbToken = user.getBithumbToken();
        this.upbitToken = user.getUpbitToken();
    }
}
