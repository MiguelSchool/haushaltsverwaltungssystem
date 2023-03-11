package com.example.haushaltsverwaltungssystem.core.controller;

import com.example.haushaltsverwaltungssystem.core.domain.AuthenticationRequest;
import com.example.haushaltsverwaltungssystem.core.domain.AuthenticationResponse;
import com.example.haushaltsverwaltungssystem.core.domain.RegisterRequest;
import com.example.haushaltsverwaltungssystem.core.domain.Token;
import com.example.haushaltsverwaltungssystem.core.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Token token) {
        authenticationService.logout(token);
    }
}
