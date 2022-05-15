package coinbuying.userservice.service;

import coinbuying.userservice.config.JwtProvider;
import coinbuying.userservice.dto.*;
import coinbuying.userservice.entity.User;
import coinbuying.userservice.exception.*;
import coinbuying.userservice.model.UserFactory;
import coinbuying.userservice.repository.UserRepository;
import coinbuying.userservice.service.encrypt.UserSha256;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import static coinbuying.userservice.entity.UserType.NORMAL;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final JwtProvider jwtProvider;

    @Value("${jwt.accessExpires}")
    private String accessExpiresString;

    @Value("${jwt.refreshExpires}")
    private String refreshExpiresString;

    /**
     * 회원가입
     */
    @Override
    public Mono<UserRegistrationResponse> memberRegistration(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(UserRegistrationRequest.class).flatMap(
                request -> userRepository.findByEmail(request.getEmail())
                        .hasElement()
                        .flatMap(alreadyMember -> {
                                    if (alreadyMember) return Mono.error(new AlreadyDataException(ExceptionMessage.AlreadyDataMember.getMessage()));

                                    return userRepository.save(
                                                    userFactory.userBuilder(
                                                            NORMAL,
                                                            request.getName(),
                                                            request.getEmail(),
                                                            request.getPassword()
                                                    )
                                            )
                                            .switchIfEmpty(Mono.error(new RegistrationFailException(ExceptionMessage.SaveFailMember.getMessage())))
                                            .flatMap(savedMember -> Mono.just(
                                                    UserRegistrationResponse.builder()
                                                            .userId(savedMember.getUserId())
                                                            .build()
                                            ));
                                }
                        )
        ).switchIfEmpty(Mono.error(new BadRequestException(ExceptionMessage.IsRequiredRequest.getMessage())));
    }

    /**
     * 로그인
     */
    @Override
    public Mono<UserLoginResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserLoginRequest.class).flatMap(
                this::memberExistCheckAndLogin
        ).switchIfEmpty(Mono.error(new BadRequestException(ExceptionMessage.IsRequiredRequest.getMessage())));
    }

    private Mono<UserLoginResponse> memberExistCheckAndLogin(UserLoginRequest request) {
        System.out.println(request.getEmail());
        Mono<User> userMono = userRepository.findByEmailAndPassword(request.getEmail(), UserSha256.encrypt(request.getPassword()));
        return userMono.hasElement()
                .flatMap(hasMember -> {
                    if (!hasMember) return Mono.error(new UnauthorizedException(ExceptionMessage.NotFoundLoginMember.getMessage()));

                    return MakeMemberLoginResponse(userMono);
                });
    }

    private Mono<UserLoginResponse> MakeMemberLoginResponse(Mono<User> userMono) {
        return userMono.flatMap(m -> {
            String accessToken = jwtProvider.createJwtToken(m, Long.parseLong(accessExpiresString));
            String refreshToken = jwtProvider.createJwtToken(m, Long.parseLong(refreshExpiresString));
            return Mono.just(new UserLoginResponse(m.getUserId(), accessToken, refreshToken));
        });
    }

    /**
     * 내 정보 조회
     */
    @Override
    public Mono<UserInfoDto> findMemberInfo(ServerRequest request) {

        int userId = Integer.parseInt(request.pathVariable("userId"));

        return userRepository.findById(userId)
                .map(UserInfoDto::new);
    }

    /**
     * 내 정보 수정
     */
    @Override
    public Mono<UserInfoDto> modifyMemberInfo(ServerRequest request) {

        int userId = Integer.parseInt(request.pathVariable("userId"));

        return userRepository.findById(userId)
                .map(UserInfoDto::new);
    }
}
