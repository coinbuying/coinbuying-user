package coinbuying.userservice.handler;

import coinbuying.userservice.dto.UserInfoResponse;
import coinbuying.userservice.dto.UserLoginResponse;
import coinbuying.userservice.dto.UserRegistrationResponse;
import coinbuying.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    /**
     * 헬스 체크
     */
    public Mono<ServerResponse> health(ServerRequest request) {
        return ok().build();
    }

    /**
     * 유저 회원 가입
     * @param request : 회원가입 정보
     * @return Mono<ServerResponse> : 등록된 유저 ID
     */
    public Mono<ServerResponse> memberRegistration(ServerRequest request) {

        Mono<UserRegistrationResponse> response = userService.memberRegistration(request)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserRegistrationResponse.class);
    }

    /**
     * 로그인
     * @param request : 로그인 정보
     * @return Mono<ServerResponse> : 권한 인증 정보
     */
    public Mono<ServerResponse> login(ServerRequest request) {

        Mono<UserLoginResponse> response = userService.login(request)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserLoginResponse.class);
    }

    /**
     * 회원 정보 조회
     * @param request : 조회할 회원 정보
     * @return Mono<ServerResponse> : 조회된 회원 정보
     */
    public Mono<ServerResponse> findMemberInfo(ServerRequest request) {

        Mono<UserInfoResponse> response = userService.findMemberInfo(request)
                .subscribeOn(Schedulers.boundedElastic());

        return  ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserInfoResponse.class);
    }
}
