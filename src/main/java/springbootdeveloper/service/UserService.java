package springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springbootdeveloper.domain.User;
import springbootdeveloper.dto.AddUserRequest;
import springbootdeveloper.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // 메서드 추가
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    //  OAuth 로그인 (10장) 에서 추가된 내용
    // 이메일을 입력받아 users 테이블에서 유저를 찾고, 없으면 예외를 발생
    // OAuth2에서 제공하는 이메일은 유일 값이므로, 해당 메서드를 통해 유저 찾기가 가능함
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
