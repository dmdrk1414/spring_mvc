package com.example.spring_mvc.service;

import lombok.RequiredArgsConstructor;
import com.example.spring_mvc.domain.User;
import com.example.spring_mvc.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public User loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException((email)));
  }
}
