package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.JwtAuthenticationResponce;
import app.komek.appkomek.model.dto.RefreshTokenRequest;
import app.komek.appkomek.model.dto.SignInRequest;
import app.komek.appkomek.model.dto.SignUpRequest;
import app.komek.appkomek.model.entity.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponce signIn(SignInRequest signInRequest);
    JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest);
}
