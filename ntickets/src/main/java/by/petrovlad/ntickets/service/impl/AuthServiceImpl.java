package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.exception.UserExistsException;
import by.petrovlad.ntickets.model.dto.JwtResponseDTO;
import by.petrovlad.ntickets.model.dto.SignInRequestDTO;
import by.petrovlad.ntickets.model.dto.SignUpRequestDTO;
import by.petrovlad.ntickets.model.entity.Role;
import by.petrovlad.ntickets.model.entity.User;
import by.petrovlad.ntickets.model.util.RoleType;
import by.petrovlad.ntickets.repository.RoleRepository;
import by.petrovlad.ntickets.repository.UserRepository;
import by.petrovlad.ntickets.security.jwt.JwtUtils;
import by.petrovlad.ntickets.security.service.UserDetailsImpl;
import by.petrovlad.ntickets.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String USERNAME_EXISTS_EXCEPTION = "User with username '%s' already exists!";
    private static final String EMAIL_DOESNT_EXISTS_EXCEPTION = "User with email '%s' doesn't exists!";
    private static final String EMAIL_EXISTS_EXCEPTION = "User with email '%s' already exists!";

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponseDTO signIn(SignInRequestDTO signInRequest) {
        if (!userRepository.existsByUsername(signInRequest.getUsername())) {
            throw new UserExistsException(EMAIL_DOESNT_EXISTS_EXCEPTION.formatted(signInRequest.getUsername()));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getEmail(), userDetails.getUsername());
    }

    @Override
    public JwtResponseDTO signUp(SignUpRequestDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserExistsException(USERNAME_EXISTS_EXCEPTION.formatted(signUpRequest.getUsername()));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserExistsException(EMAIL_EXISTS_EXCEPTION.formatted(signUpRequest.getEmail()));
        }

        // Create new user's account
        Set<String> strRoles = new HashSet<>(signUpRequest.getRoles());
        Set<Role> roles = new HashSet<>();

        if (strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "moderator" -> {
                        Role modRole = roleRepository.findByName(RoleType.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        User user = new User(null, signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), roles);
        user = userRepository.save(user);

        return signIn(new SignInRequestDTO(signUpRequest.getUsername(), signUpRequest.getPassword()));
    }
}
