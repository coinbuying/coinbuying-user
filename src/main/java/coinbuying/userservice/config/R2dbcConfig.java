package coinbuying.userservice.config;

import coinbuying.userservice.model.UserFactory;
import coinbuying.userservice.repository.UserRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class R2dbcConfig {

    private final UserFactory userFactory;

    public R2dbcConfig(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Bean
    public ConnectionFactoryInitializer dbInit(ConnectionFactory connectionFactory) { // 데이터베이스 초기화를 할수 있음 ConnectionFactoryInitializer
        ConnectionFactoryInitializer init = new ConnectionFactoryInitializer();
        init.setConnectionFactory(connectionFactory);
        init.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql"))); // 커넥션꺼
        return init;

    }

    @Bean
    public CommandLineRunner dataInit(UserRepository userRepository) { // ioc에서 해당하는 userRepository 주입이 된다.
        return (args) -> { // run 함수
            // 데이터 초기화
            userRepository.saveAll(
                    userFactory.setupListBuilder()
            ).blockLast(); // 끝인걸 알려줘야함
        };
    }


}
