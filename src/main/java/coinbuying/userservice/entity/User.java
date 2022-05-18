package coinbuying.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value="users")
@AllArgsConstructor
@Getter
@Builder
public class User {

    @Id
    @Column(value="user_id")
    private int userId;

    @Column(value="user_type")
    private UserType userType;

    @Column(value="name")
    private String name;

    @Column(value="email")
    private String email;

    @Column(value="password")
    private String password;

    @Column(value="bithumb_token")
    private String bithumbToken;

    @Column(value="upbit_token")
    private String upbitToken;

    @Column(value="create_dt")
    private LocalDateTime createDt;

    @Column(value="update_dt")
    private LocalDateTime updateDt;


}
