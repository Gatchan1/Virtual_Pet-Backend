package cat.itacademy.s05.t02.VirtualPet.controller;

import cat.itacademy.s05.t02.VirtualPet.dto.PetCreateRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetFindRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetUpdateRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetWithUserInfo;
import cat.itacademy.s05.t02.VirtualPet.model.Pet;
import cat.itacademy.s05.t02.VirtualPet.service.impl.PetServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetServiceImpl petService;

    @PostMapping("/create")
    public ResponseEntity<Pet> createPet(@RequestBody @Valid PetCreateRequest petCreateRequest) {
        return new ResponseEntity<>(petService.createPet(petCreateRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePet(@RequestBody PetFindRequest petFindRequest) {
        petService.deletePet(petFindRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne")
    public ResponseEntity<Pet> getOnePet(@RequestParam String userId, @RequestParam String name) {
        PetFindRequest petFindRequest = PetFindRequest.builder().userId(userId).name(name).build();
        return ResponseEntity.ok(petService.getSinglePet(petFindRequest));
    }

    @GetMapping("/getUserPets")
    public ResponseEntity<List<Pet>> getUserPets(@RequestParam String userId) {
        return ResponseEntity.ok(petService.getUserPets(userId));
    }

    @GetMapping("/admin/getAll")
    public ResponseEntity<List<PetWithUserInfo>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @PatchMapping("/update")
    public ResponseEntity<Pet> updatePet(@RequestBody PetUpdateRequest petUpdateRequest) {
        return ResponseEntity.ok(petService.updatePet(petUpdateRequest));
    }
}
