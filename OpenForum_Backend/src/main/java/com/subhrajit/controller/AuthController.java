package com.subhrajit.controller;

import com.subhrajit.dto.AuthenticationResponse;
import com.subhrajit.dto.LoginRequest;
import com.subhrajit.dto.RefreshTokenRequest;
import com.subhrajit.dto.RegisterRequest;
import com.subhrajit.service.AuthService;
import com.subhrajit.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(
            @PathVariable String token
    ){
        authService.verifyToken(token);
        return new ResponseEntity<>("Account Verification Successful",OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(
            @RequestBody LoginRequest loginRequest
    ) {
        return authService.login(loginRequest);
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest
    ) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

}
