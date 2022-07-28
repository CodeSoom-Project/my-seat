package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "test";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9" +
            ".eyJ1c2VySWQiOjF9" +
            ".ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService service;

    private LoginRequest request;

    @Test
    @DisplayName("로그인 테스트")
    void test() throws Exception {
        // given
        request = LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        given(service.login(any(LoginRequest.class)))
                .willReturn(TOKEN);

        // when
        ResultActions subject = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        subject.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(TOKEN));
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
