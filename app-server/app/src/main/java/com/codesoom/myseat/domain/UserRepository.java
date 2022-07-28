package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 회원 레포지토리
 */
public interface UserRepository 
        extends CrudRepository<User, Long> {

    /**
     * 회원 정보를 저장한다.
     * 
     * @param user must not be {@literal null}.
     * @return 회원 정보
     */
    User save(User user);

    /**
     * 이메일로 회원 정보를 조회한다.
     * 
     * @param email 이메일
     * @return 회원 정보
     */
    Optional<User> findByEmail(String email);
}
