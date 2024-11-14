package cat.itacademy.s05.t02.VirtualPet.controller;

import cat.itacademy.s05.t02.VirtualPet.service.impl.PetServiceImpl;
import cat.itacademy.s05.t02.VirtualPet.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final PetServiceImpl petService;

    @GetMapping("/admin/testAdmin")                   //TODO BORRAR!!
    public ResponseEntity<String> seyHelloAdmin() {
        return ResponseEntity.ok("Hello adminnn!");
    }

    @GetMapping("/testUser")
    public ResponseEntity<String> seyHelloUser() {    //TODO BORRAR!!
        return ResponseEntity.ok("Hello userrr!");
    }

    //Nada de esto funciona ahora mismo pq en la SecurityConfig
    //no tengo configuración para la ruta /user/** !!

}
