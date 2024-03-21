package com.example.spring_mvc.service;

import lombok.RequiredArgsConstructor;
import com.example.spring_mvc.domain.User;
import com.example.spring_mvc.dto.AddUserRequest;
import com.example.spring_mvc.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Long save(AddUserRequest dto) {
    return userRepository.save(User.builder()
        .email(dto.getEmail())
        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
        .build()).getId();
  }
}
