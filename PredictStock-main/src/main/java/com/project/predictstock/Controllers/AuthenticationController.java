package com.project.predictstock.Controllers;

import com.project.predictstock.DTO.AuthenticationResponse;
import com.project.predictstock.DTO.LoginRequest;
import com.project.predictstock.DTO.RegisterRequest;
import com.project.predictstock.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(userService.register(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody LoginRequest request
    ){
        return  ResponseEntity.ok(userService.login(request));
    }
}
