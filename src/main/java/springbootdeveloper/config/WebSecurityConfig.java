// 10장보다 앞의 기존 폼의 로그인 방식
//package springbootdeveloper.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import springbootdeveloper.service.UserDetailService;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebSecurityConfig {
//    private final UserDetailService userService;
//
//    // 스프링 시큐리티 기능 비활성화
//    @Bean
//    public WebSecurityCustomizer configure() {
//        // H2 데이터베이스 콘솔에 대한 요청과 "/static/"로 시작하는 모든 요청을 무시하도록 지정
//        // 이를 통해 해당 요청들은 Spring Security의 인증 및 권한 검사를 받지 않고 처리
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }
//
//    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                // 인증, 인가 설정
//                .authorizeRequests()
//                // "/login", "/signup", "/user" 에 대한 요청은 인증 없이 허용
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                // 다른 모든 요청에 대해서는 인증을 필요로 함
//                .anyRequest().authenticated()
//                .and()
//                // 폼 기반 로그인 설정
//                .formLogin()
//                // 사용자 정의 로그인 페이지의 URL을 "/login"으로 설정
//                .loginPage("/login")
//                // 로그인 성공 후 기본적으로 이동할 페이지의 URL을 "/articles"로 설정
//                .defaultSuccessUrl("/articles")
//                .and()
//                // 로그아웃 설정
//                .logout()
//                // 로그아웃 성공 후 이동할 페이지의 URL을 "/login"으로 설정
//                .logoutSuccessUrl("/login")
//                // 로그아웃 시 HTTP 세션을 무효화하는 설정
//                .invalidateHttpSession(true)
//                .and()
//                // csrf(Cross-Site Request Forgery) 비활성화
//                .csrf().disable()
//                .build();
//    }
//
//    // 인증 관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
//        return http
//                // HttpSecurity 객체로부터 AuthenticationManagerBuilder를 가져오는 메서드
//                .getSharedObject(AuthenticationManagerBuilder.class)
//                // 사용자 정보 서비스 설정
//                .userDetailsService(userService)
//                // 비밀번호 인코더(BCryptPasswordEncoder)를 설정하는 메서드. 비밀번호를 안전하게 해시하여 저장하고 검증하는 데 사용됨
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
//    // 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}