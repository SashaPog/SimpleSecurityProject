package demoapp.newjwtspringsecurity.controller;

import demoapp.newjwtspringsecurity.dto.AuthenticationRequestDto;
import demoapp.newjwtspringsecurity.dto.AuthenticationResponceDto;
import demoapp.newjwtspringsecurity.dto.RegisterRequestDto;
import demoapp.newjwtspringsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponceDto> register(
        @RequestBody RegisterRequestDto request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponceDto> register(
        @RequestBody AuthenticationRequestDto request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
