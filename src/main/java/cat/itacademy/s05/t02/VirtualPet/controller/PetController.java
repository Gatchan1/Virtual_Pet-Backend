package cat.itacademy.s05.t02.VirtualPet.controller;

import cat.itacademy.s05.t02.VirtualPet.dto.PetCreateRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetFindRequest;
import cat.itacademy.s05.t02.VirtualPet.model.Pet;
import cat.itacademy.s05.t02.VirtualPet.service.impl.PetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetServiceImpl petService;

    @PostMapping("/create")
    public ResponseEntity<Pet> createPet(@RequestBody PetCreateRequest petCreateRequest) {
        return new ResponseEntity<>(petService.createPet(petCreateRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePet(@RequestBody PetFindRequest petFindRequest) {
        petService.deletePet(petFindRequest);
        return ResponseEntity.noContent().build();
    }

    //@GetMapping("/read")
}
