package coinbuying.userservice.dto;

import coinbuying.userservice.entity.User;
import coinbuying.userservice.entity.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDto {
    private String name;
    private String email;
    private UserType userType;

    @Builder
    public UserInfoDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.userType = user.getUserType();
    }
}
