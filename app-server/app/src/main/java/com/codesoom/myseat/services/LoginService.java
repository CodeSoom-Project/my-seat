package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.UserRepository;
import com.codesoom.myseat.dto.LoginRequest;
import org.springframework.stereotype.Service;

/**
 * 로그인 서비스
 */
@Service
public class LoginService {
    private final UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
    }

    public String login(LoginRequest request) {
        // TODO: 인증
        //  - 로그인 dto의 비밀번호가 회원 정보와 일치하는지 확인
        //  - 일치하면 유효한 토큰 반환
        //  - 일치하지 않으면 예외 던짐
        return null;
    }
}
