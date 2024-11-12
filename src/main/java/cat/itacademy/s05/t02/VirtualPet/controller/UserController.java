package cat.itacademy.s05.t02.VirtualPet.controller;

import cat.itacademy.s05.t02.VirtualPet.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/admin/testAdmin")
    public ResponseEntity<String> seyHelloAdmin() {
        return ResponseEntity.ok("Hello adminnn!");
    }

    @GetMapping("/testUser")
    public ResponseEntity<String> seyHelloUser() {
        return ResponseEntity.ok("Hello userrr!");
    }
}
