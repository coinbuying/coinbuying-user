package coinbuying.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserType {
    //1. 일반회원, 2. 운영자
    NORMAL("NORMAL", "일반회원"),
    ADMIN("ADMIN", "운영자");

    private String name;
    private String description;

}
