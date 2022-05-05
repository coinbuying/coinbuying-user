package coinbuying.userservice.repository;

import coinbuying.userservice.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<User, Integer> {

}
