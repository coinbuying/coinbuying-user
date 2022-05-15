package coinbuying.userservice.model;

import coinbuying.userservice.entity.User;
import coinbuying.userservice.entity.UserType;

import java.time.LocalDateTime;
import java.util.List;

public interface UserFactory {
    User userBuilder(UserType userType, String name, String email, String password);
    List<User> setupListBuilder();
}
