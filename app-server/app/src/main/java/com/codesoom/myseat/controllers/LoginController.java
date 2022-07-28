package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.dto.LoginResponse;
import com.codesoom.myseat.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 로그인 컨트롤러
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(
        origins = "https://codesoom-project.github.io",
        allowedHeaders = "*",
        allowCredentials = "true")
public class LoginController {
    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    /**
     * 로그인 후 상태코드 200과 토큰을 응답한다.
     * 
     * @param request
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return toResponse(service.login(request));
    }

    /**
     * 응답 정보를 반환한다.
     *
     * @param token 토큰
     * @return 응답 정보
     */
    private LoginResponse toResponse(String token) {
        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
