package app.komek.appkomek.controller;

import app.komek.appkomek.model.dto.*;
import app.komek.appkomek.service.AuthenticationService;
import app.komek.appkomek.service.ChangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ChangeService changeService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest){
        authenticationService.signUp(signUpRequest);
        return new ResponseEntity<>("Аккаунт успешно сохранен!", HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponce> signIn(@RequestBody SignInRequest signInRequest){
        return new ResponseEntity<>(authenticationService.signIn(signInRequest), HttpStatus.FOUND);
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponce> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return new ResponseEntity<>(authenticationService.refreshToken(refreshTokenRequest), HttpStatus.CONTINUE);
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto dto) {
        changeService.changePassword(dto);
        return ResponseEntity.ok("Пароль успешно изменен");
    }
}
