package cat.itacademy.s05.t02.VirtualPet.service;

import cat.itacademy.s05.t02.VirtualPet.dto.PetCreateRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetFindRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetUpdateRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetWithUserInfo;
import cat.itacademy.s05.t02.VirtualPet.model.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(PetCreateRequest petCreateRequest);
    void deletePet(PetFindRequest petFindRequest);
    Pet getSinglePet(PetFindRequest petFindRequest);
    List<Pet> getUserPets(String userId);
    List<PetWithUserInfo> getAllPets();
    Pet updatePet(PetUpdateRequest petUpdateRequest);
}
