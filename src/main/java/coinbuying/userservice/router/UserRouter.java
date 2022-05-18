package coinbuying.userservice.router;

import coinbuying.userservice.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> userPathRouter(UserHandler userHandler) {
        return RouterFunctions
                .route()
                .POST("/user/signup", accept(APPLICATION_JSON), userHandler::memberRegistration)// 회원가입
                .POST("/user/login", accept(APPLICATION_JSON), userHandler::login)       // 로그인
                .GET("/user/{userId}", userHandler::findMemberInfo)        // 유저정보조회
                .GET("/user", userHandler::health)
                .build();
    }
}
