package demoapp.newjwtspringsecurity.service;

import demoapp.newjwtspringsecurity.dto.AuthenticationRequestDto;
import demoapp.newjwtspringsecurity.dto.AuthenticationResponceDto;
import demoapp.newjwtspringsecurity.dto.RegisterRequestDto;
import demoapp.newjwtspringsecurity.enums.Role;
import demoapp.newjwtspringsecurity.mapper.RegisterRequestDtoUserMapper;
import demoapp.newjwtspringsecurity.model.User;
import demoapp.newjwtspringsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final RegisterRequestDtoUserMapper registerRequestDtoUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public AuthenticationResponceDto register(RegisterRequestDto dto) {
        User user = registerRequestDtoUserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        repository.save(user);
        return getAuthenticationResponceDto(user);
    }

    public AuthenticationResponceDto authenticate(AuthenticationRequestDto dto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
            )
        );
        var user = repository.findByEmail(dto.getEmail()).orElseThrow();
        return getAuthenticationResponceDto(user);
    }

    private AuthenticationResponceDto getAuthenticationResponceDto(User user) {
        var jwtToken = jwtService.generateToken(new CustomUserDetailService(user));
        return AuthenticationResponceDto.builder()
            .token(jwtToken)
            .build();
    }
}
