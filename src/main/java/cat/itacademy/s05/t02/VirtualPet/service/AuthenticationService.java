package cat.itacademy.s05.t02.VirtualPet.service;

import cat.itacademy.s05.t02.VirtualPet.model.dto.AuthenticationRequest;
import cat.itacademy.s05.t02.VirtualPet.model.dto.RegisterRequest;

public interface AuthenticationService {
    String register(RegisterRequest registerRequest);
    String authenticate(AuthenticationRequest authenticationRequest);
}
