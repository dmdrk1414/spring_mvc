package com.example.spring_mvc.config;

import com.example.spring_mvc.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

  private final UserDetailService userService;

  /**
   * 스프링 시큐리티 기능 비활성화 스프링 시큐리티의 일부 기능을 비활성화하는 코드입니다. 일반적으로 정적 리소스와 h2 데이터베이스 관련 URL에 적용됩니다.
   *
   * @return
   */
  @Bean
  public WebSecurityCustomizer configure() {
    return (web) -> web.ignoring()
        .requestMatchers(toH2Console())
        .requestMatchers("/static/**");
  }

  /**
   * 특정 HTTP 요청에 대한 웹 기반 보안 구성, 웹 기반 보안을 설정하기 위한 코드입니다. 이 부분에서는 인증, 인가, 로그인, 로그아웃 관련 설정을 할 수 있습니다.
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        // 인증, 인가 설정
        .authorizeRequests()
        // 특정 요청과 일치하는 URL에 대한 액세스를 설정합니다. 이 메서드를 사용하여 특정 요청에 대한 보안 구성을 정의할 수 있습니다.
        // 주로 어떤 URL 패턴에 대해 어떤 보안 설정을 할 것인지를 지정합니다.
        .requestMatchers("/login", "/signup", "/user")
        //  누구나 접근할 수 있도록 설정합니다. 즉, "/login", "/signup", "/user"와 같은 URL로 요청이 오면,
        //  인증 및 인가 없이도 접근할 수 있습니다.
        //  일반적으로 로그인 페이지나 회원가입 페이지와 같이 모든 사용자가 접근할 수 있는 URL에 이 설정을 사용합니다.
        .permitAll()
        // 앞서 설정한 URL 이외의 모든 요청에 대해 설정합니다.
        // 즉, 이전에 명시적으로 설정하지 않은 모든 URL에 대해 해당 설정이 적용됩니다.
        .anyRequest()
        // 인증된 사용자만 접근할 수 있도록 설정합니다. 인가는 필요하지 않지만,
        // 요청을 보내는 사용자가 로그인되어 있어야 합니다. 이 설정은 보통 인증된 사용자에게만 허용되는 리소스나 페이지에 사용됩니다.
        .authenticated()
        .and()
        // 폼 기반 로그인 설정
        .formLogin()
        // 로그인 페이지 경로를 설정합니다.
        .loginPage("/login")
        // 로그인이 완료되었을 때 이동할 경로를 설정합니다.
        .defaultSuccessUrl("/articles")
        .and()
        // 로그아웃 설정
        .logout()
        // 로그아웃이 완료되었을 때 이동할 경로를 설정합니다.
        .logoutSuccessUrl("/login")
        // 로그아웃 후에 세션을 전체 삭제할지 여부를 설정합니다.
        .invalidateHttpSession(true)
        .and()
        // csrf 비활성화
        .csrf().disable()
        .build();
  }

  /**
   * 인증 관리자 관련 설정
   *
   * @param http
   * @param bCryptPasswordEncoder
   * @param userDetailService
   * @return
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http,
      BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
      throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userService)
        .passwordEncoder(bCryptPasswordEncoder)
        .and()
        .build();
  }

  /**
   * 패스워드 인코더로 사용할 빈 등록
   *
   * @return
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
