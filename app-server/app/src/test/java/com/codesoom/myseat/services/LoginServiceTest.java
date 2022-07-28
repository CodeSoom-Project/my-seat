package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.domain.UserRepository;
import com.codesoom.myseat.dto.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "test";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9" +
            ".eyJ1c2VySWQiOjF9" +
            ".ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final Long USER_ID = 1L;
    private static final String NAME = "코드숨";

    private LoginService service;
    private final UserRepository repository = mock(UserRepository.class);

    private LoginRequest request;
    private User user;

    @BeforeEach
    void setUp() {
        service = new LoginService(repository);
    }

    @Nested
    @DisplayName("login 메서드는")
    class Describe_login_method {
        @BeforeEach
        void setUp() {
            request = LoginRequest.builder()
                    .email(EMAIL)
                    .password(PASSWORD)
                    .build();
            
            user = User.builder()
                    .id(USER_ID)
                    .name(NAME)
                    .email(EMAIL)
                    .password(PASSWORD)
                    .build();

            given(repository.findByEmail(eq(EMAIL)))
                    .willReturn(Optional.of(user));
        }

        @Nested
        @DisplayName("토큰을 반환한다")
        class It_returns_token {
            String subject() {
                return service.login(request);
            }

            @Test
            void test() {
                assertThat(subject()).isEqualTo(TOKEN);
            }
        }
    }
}
