package coinbuying.userservice.model;

import coinbuying.userservice.entity.User;
import coinbuying.userservice.entity.UserType;
import coinbuying.userservice.service.encrypt.UserSha256;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFactoryImpl implements UserFactory{

    @Override
    public User userBuilder(UserType userType, String name, String email, String password) {
        return User.builder()
                .userType(userType)
                .name(name)
                .email(email)
                .password(UserSha256.encrypt(password))
                .build();
    }

    @Override
    public List<User> setupListBuilder() {
        return Arrays.asList(
                this.userBuilder(UserType.ADMIN, "설동찬", "1231@naver.com", "12345"),
                this.userBuilder(UserType.ADMIN, "최성우", "1232@naver.com", "12345"),
                this.userBuilder(UserType.ADMIN, "박정수", "1233@naver.com", "12345"),
                this.userBuilder(UserType.ADMIN, "이휘수", "1234@naver.com", "12345")
        );
    }
}