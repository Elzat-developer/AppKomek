package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.JwtAuthenticationResponce;
import app.komek.appkomek.model.dto.RefreshTokenRequest;
import app.komek.appkomek.model.dto.SignInRequest;
import app.komek.appkomek.model.dto.SignUpRequest;
import app.komek.appkomek.model.entity.User;
import app.komek.appkomek.model.role.Authorities;
import app.komek.appkomek.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public User signUp(SignUpRequest signUpRequest){
        User user = new User();

        user.setEmail(signUpRequest.email());
        user.setName(signUpRequest.name());
        user.setAuthorities(Authorities.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));

        return userRepo.save(user);
    }
    public JwtAuthenticationResponce signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.email(),
                signInRequest.password()));
        var user = userRepo.findByEmail(signInRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (user.isPasswordTemporary()){
            throw new IllegalArgumentException("Необходимо сменить временный пароль");
        }
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefrechToken(new HashMap<>(), user);

        return new JwtAuthenticationResponce(jwt,refreshToken);
    }
    public JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.token());
        User user = userRepo.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.token(),user)){
            var jwt = jwtService.generateToken(user);

            return new JwtAuthenticationResponce(jwt,refreshTokenRequest.token());
        }
        return null;
    }
}
