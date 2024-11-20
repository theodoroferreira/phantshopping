package com.api.phantshopping.framework.controller;

import com.api.phantshopping.domain.dto.request.LoginRequestDto;
import com.api.phantshopping.domain.dto.response.LoginResponseDto;
import com.api.phantshopping.domain.model.User;
import com.api.phantshopping.framework.config.security.JwtTokenService;
import com.api.phantshopping.framework.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String jwt = jwtTokenService.generateToken((UserDetailsImpl) authentication.getPrincipal());

        return ResponseEntity.ok(LoginResponseDto.builder()
                        .token(jwt)
                .build());
    }
}
