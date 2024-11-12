package cat.itacademy.s05.t02.VirtualPet.service.impl;

import cat.itacademy.s05.t02.VirtualPet.enums.Role;
import cat.itacademy.s05.t02.VirtualPet.exception.custom.UserAlreadyExistsException;
import cat.itacademy.s05.t02.VirtualPet.model.User;
import cat.itacademy.s05.t02.VirtualPet.dto.AuthenticationRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.RegisterRequest;
import cat.itacademy.s05.t02.VirtualPet.repository.UserRepository;
import cat.itacademy.s05.t02.VirtualPet.service.AuthenticationService;
import cat.itacademy.s05.t02.VirtualPet.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest registerRequest) {
        userRepository.findByUserName(registerRequest.getUserName())
                .ifPresent(existingFruit -> {
                    throw new UserAlreadyExistsException("User with username '" +
                            registerRequest.getUserName() + "' already exists");
                });
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .hashedPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return jwtProvider.generateToken(user);
    }

    @Override
    public String authenticate(AuthenticationRequest authenticationRequest) { //login
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()
        ));
        User user = userRepository.findByUserName(authenticationRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User with username " +
                        authenticationRequest.getUserName() + " not found"));
        return jwtProvider.generateToken(user);
    }
}
