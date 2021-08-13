package by.petrovlad.ntickets.service;

import by.petrovlad.ntickets.model.dto.JwtResponseDTO;
import by.petrovlad.ntickets.model.dto.SignInRequestDTO;
import by.petrovlad.ntickets.model.dto.SignUpRequestDTO;

public interface AuthService {
    JwtResponseDTO signIn(SignInRequestDTO signInRequest);
    JwtResponseDTO signUp(SignUpRequestDTO signUpRequest);
}
