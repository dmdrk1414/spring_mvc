package com.example.spring_mvc.service;

import lombok.RequiredArgsConstructor;
import com.example.spring_mvc.domain.User;
import com.example.spring_mvc.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 스프링 시큐리티에서 로그인을 진행할 때 사용자 정보를 가져오는 코드
 */
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService { // 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스

  private final UserRepository userRepository;

  /**
   * 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드 s
   *
   * @param email the username identifying the user whose data is required.
   * @return
   */
  @Override
  public User loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException((email)));
  }
}
