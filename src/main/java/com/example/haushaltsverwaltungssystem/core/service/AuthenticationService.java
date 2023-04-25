package com.example.haushaltsverwaltungssystem.core.service;

import com.example.haushaltsverwaltungssystem.core.domain.*;
import com.example.haushaltsverwaltungssystem.core.repository.TokenRepository;
import com.example.haushaltsverwaltungssystem.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        log.info("dddd:",registerRequest.getFirstName());
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        log.info("user" + user.getEmail());
        log.info("user" + user.getFirstName());
        log.info("user" + user.getLastName());
        log.info("user" + user.getEmail());
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getEmail(),
                        registerRequest.getPassword()
                )
        );
        var user = userRepository.findUserByIdOrElseThrow(registerRequest.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void logout(Token token) {
        tokenRepository.delete(token);
    }

    public User getProfile(UserDetails userDetails) {
        log.info("get profile from database");
        User profile = findUserByEmail(userDetails.getUsername());
        if(profile == null) {
            User createNewProfile = new User();
            createNewProfile.setCreatedDate(LocalDate.now());
            createNewProfile.setUpdatedDate(LocalDate.now());
            createNewProfile.setEmail(userDetails.getUsername());
            createNewProfile.setRole(Role.USER);
            createNewProfile.setImage(null);
            createProfile(createNewProfile);
            return createNewProfile;
        }
        return profile;
    }

    public User createProfile(User profile) {
        return userRepository.save(profile);
    }

    public User updateUser(User profile) {
        User actual = userRepository.findUserByIdOrElseThrow(profile.getId());
        actual.setRole(profile.getRole());
        actual.setUsername(profile.getUsername());
        actual.setEmail(profile.getEmail());
        actual.setFirstName(profile.getFirstName());
        actual.setLastName(profile.getLastName());
        actual.setAddress(profile.getAddress());
        profile.setUpdatedDate(LocalDate.now());
        return createProfile(profile);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByIdOrElseThrow(email);
    }

    public User findUserById(Long id) {
        return userRepository.findUserByIdOrElseThrow(id);
    }
}
