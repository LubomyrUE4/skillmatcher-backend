package com.example.skillmatcherbackend.controller;

import com.example.skillmatcherbackend.exception.DefaultException;
import com.example.skillmatcherbackend.model.dto.RefreshTokenDTO;
import com.example.skillmatcherbackend.model.dto.SignInDTO;
import com.example.skillmatcherbackend.model.dto.SignUpDTO;
import com.example.skillmatcherbackend.service.JwtService;
import com.example.skillmatcherbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signIn")
    public void signIn(@RequestBody final SignInDTO signInDTO) {
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody final SignUpDTO signUpDTO) throws DefaultException {
        return new ResponseEntity<>(jwtService.generateToken(userService.saveUser(signUpDTO)), HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody final RefreshTokenDTO refreshTokenDTO) throws DefaultException {
        return new ResponseEntity<>(jwtService.generateNewToken(refreshTokenDTO.getRefreshToken()), HttpStatus.OK);
    }
}