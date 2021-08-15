package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.dto.JwtResponseDTO;
import by.petrovlad.ntickets.model.dto.SignInRequestDTO;
import by.petrovlad.ntickets.model.dto.SignUpRequestDTO;
import by.petrovlad.ntickets.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public JwtResponseDTO signIn(@RequestBody @Valid SignInRequestDTO signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/signup")
    public JwtResponseDTO registerUser(@RequestBody @Valid SignUpRequestDTO signUpRequest) {
        return authService.signUp(signUpRequest);
    }
}
