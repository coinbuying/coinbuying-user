package coinbuying.userservice.service;

import coinbuying.userservice.dto.UserInfoResponse;
import coinbuying.userservice.dto.UserLoginResponse;
import coinbuying.userservice.dto.UserRegistrationResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface UserService {

    /**
     * 회원가입
     * 로그인
     * 회원정보조회
     * 회원정보수정
     */

    Mono<UserRegistrationResponse> memberRegistration(ServerRequest request); // 회원가입

    Mono<UserLoginResponse> login(ServerRequest request); // 로그인

    Mono<UserInfoResponse> findMemberInfo(ServerRequest request); // 회원 정보 조회

}
