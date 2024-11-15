package cat.itacademy.s05.t02.VirtualPet.controller;

import cat.itacademy.s05.t02.VirtualPet.dto.AuthenticationRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.RegisterRequest;
import cat.itacademy.s05.t02.VirtualPet.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String jwtToken = authService.register(registerRequest);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        String jwtToken = authService.authenticate(authenticationRequest);
        return ResponseEntity.ok(jwtToken);
    }
}
