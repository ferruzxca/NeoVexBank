package com.innovex.neovexbank.service;

import com.innovex.neovexbank.dto.AuthRequest;
import com.innovex.neovexbank.dto.AuthResponse;
import com.innovex.neovexbank.model.User;
import com.innovex.neovexbank.repository.UserRepository;
import com.innovex.neovexbank.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        // autentica
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        // carga usuario
        User user = userRepo.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // genera token
        String token = jwtService.generateToken(user);

        // devuelve token + rol
        return new AuthResponse(token, user.getRole().name());
    }
}