package cat.itacademy.s05.t02.VirtualPet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController                                //This file can be deleted
@RequestMapping("/test")
public class TestController {
    @GetMapping("/")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Everything ok");
    }
}
