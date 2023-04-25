package com.example.haushaltsverwaltungssystem.core.controller;

import com.example.haushaltsverwaltungssystem.core.domain.*;
import com.example.haushaltsverwaltungssystem.core.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:4200/**", "http://localhost:4200/"},exposedHeaders = "Authorization", allowCredentials = "true", maxAge = 3600)
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
            return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Token token) {
        authenticationService.logout(token);
    }

    @GetMapping("/authorities")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getProfileFromLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.getProfile(userDetails));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User>getProfileByEmail(@RequestBody String email) {
        return ResponseEntity.ok(authenticationService.findUserByEmail(email));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User>getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(authenticationService.findUserById(id));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User>updateProfile(@RequestBody User profile) {
        return ResponseEntity.ok(authenticationService.updateUser(profile));
    }
}
