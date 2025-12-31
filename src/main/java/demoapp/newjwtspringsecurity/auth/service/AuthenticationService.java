package demoapp.newjwtspringsecurity.auth.service;

import demoapp.newjwtspringsecurity.auth.dto.AuthenticationRequestDto;
import demoapp.newjwtspringsecurity.auth.dto.AuthenticationResponceDto;
import demoapp.newjwtspringsecurity.auth.dto.RegisterRequestDto;
import demoapp.newjwtspringsecurity.user.enums.Role;
import demoapp.newjwtspringsecurity.auth.dto.mapper.RegisterRequestDtoUserMapper;
import demoapp.newjwtspringsecurity.user.domain.User;
import demoapp.newjwtspringsecurity.user.repository.UserRepository;
import demoapp.newjwtspringsecurity.security.userdetails.CustomUserDetailService;
import demoapp.newjwtspringsecurity.security.jwt.JwtService;
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
