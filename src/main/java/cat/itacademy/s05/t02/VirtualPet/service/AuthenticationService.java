package cat.itacademy.s05.t02.VirtualPet.service;

import cat.itacademy.s05.t02.VirtualPet.dto.AuthenticationRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.RegisterRequest;

public interface AuthenticationService {
    String register(RegisterRequest registerRequest);
    String authenticate(AuthenticationRequest authenticationRequest);
}
