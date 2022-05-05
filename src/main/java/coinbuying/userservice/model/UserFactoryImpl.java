package coinbuying.userservice.model;

import coinbuying.userservice.entity.User;
import coinbuying.userservice.entity.UserType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFactoryImpl implements UserFactory{

    @Override
    public User userBuilder(UserType userType, String name, String email, String password, LocalDateTime createDt, LocalDateTime updateDt) {
        return User.builder()
                .userType(userType)
                .name(name)
                .email(email)
                .password(password)
                .createDt(createDt)
                .updateDt(updateDt)
                .build();
    }

    @Override
    public List<User> setupListBuilder() {
        return Arrays.asList(
                this.userBuilder(UserType.ADMIN, "설동찬", "123@naver.com", "12345", LocalDateTime.now(), LocalDateTime.now()),
                this.userBuilder(UserType.ADMIN, "최성우", "123@naver.com", "12345", LocalDateTime.now(), LocalDateTime.now()),
                this.userBuilder(UserType.ADMIN, "박정수", "123@naver.com", "12345", LocalDateTime.now(), LocalDateTime.now()),
                this.userBuilder(UserType.ADMIN, "주태윤", "123@naver.com", "12345", LocalDateTime.now(), LocalDateTime.now()),
                this.userBuilder(UserType.ADMIN, "이휘수", "123@naver.com", "12345", LocalDateTime.now(), LocalDateTime.now())
        );
    }
}
