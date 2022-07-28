package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 응답 정보
 */
@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
