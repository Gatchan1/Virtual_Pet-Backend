package cat.itacademy.s05.t02.VirtualPet.service.impl;

import cat.itacademy.s05.t02.VirtualPet.dto.PetCreateRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetFindRequest;
import cat.itacademy.s05.t02.VirtualPet.dto.PetUpdateRequest;
import cat.itacademy.s05.t02.VirtualPet.enums.Accessory;
import cat.itacademy.s05.t02.VirtualPet.enums.Location;
import cat.itacademy.s05.t02.VirtualPet.enums.PetInteraction;
import cat.itacademy.s05.t02.VirtualPet.enums.TasteLevel;
import cat.itacademy.s05.t02.VirtualPet.exception.custom.PetNameAlreadyExistsException;
import cat.itacademy.s05.t02.VirtualPet.exception.custom.PetNameNotFoundException;
import cat.itacademy.s05.t02.VirtualPet.exception.custom.UserNotFoundException;
import cat.itacademy.s05.t02.VirtualPet.model.Pet;
import cat.itacademy.s05.t02.VirtualPet.model.User;
import cat.itacademy.s05.t02.VirtualPet.repository.UserRepository;
import cat.itacademy.s05.t02.VirtualPet.service.PetService;
import cat.itacademy.s05.t02.VirtualPet.util.EnumUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final UserRepository userRepository;

    private void updateAllPets(Consumer<Pet> petAction) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.getPets().forEach(petAction));
        userRepository.saveAll(users);
    }

    @Scheduled(fixedRate = 30000) // 3600000 = 1 hour    //TODO change back to 3600000
    private void scheduledPetsBehaviourUpdate() {
        updateAllPets(Pet::hourlyBehaviourUpdate);
    }

    @Scheduled(fixedRate = 720000) // 720000 = 12 minutes //TODO delete this method
    private void scheduledPetsSleep() {
        updateAllPets(pet -> pet.setAsleep(!pet.isAsleep()));
    }

    @Override
    public Pet createPet(PetCreateRequest petCreateRequest) {
        String userId = petCreateRequest.getUserId();
        String petName = petCreateRequest.getName();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        user.retrievePetByName(petName).ifPresent(pet -> {
            throw new PetNameAlreadyExistsException("Pet with name " + petName +
                    " already exists for user with id " + userId);
        });
        Pet newPet = buildPet(petCreateRequest);
        user.getPets().add(newPet);
        userRepository.save(user);
        return newPet;
    }

    private Pet buildPet(PetCreateRequest petCreateRequest) {
        Pet newPet = Pet.builder()
                .name(petCreateRequest.getName())
                .type(petCreateRequest.getType())
                .color(petCreateRequest.getColor())
                .happiness(40)
                .energy(60)
                .accessories(new HashSet<>())
                .location(Location.COUNTRYSIDE)
                .build();
        newPet.setAccessoryPreferences(generateAccessoryPreferences());
        newPet.setLocationPreferences(generateLocationPreferences());
        return newPet;
    }

    public Map<Accessory, TasteLevel> generateAccessoryPreferences() {
        return generatePreferences(Accessory.class);
    }

    public Map<Location, TasteLevel> generateLocationPreferences() {
        return generatePreferences(Location.class);
    }

    private <T extends Enum<T>> Map<T, TasteLevel> generatePreferences(Class<T> enumClass) {
        return Stream.of(enumClass.getEnumConstants())
                .collect(Collectors.toMap(
                        enumConstant -> enumConstant,
                        enumConstant -> EnumUtils.getRandomEnumValue(TasteLevel.class)
                ));
    }

    @Override
    public void deletePet(PetFindRequest petFindRequest) {
        User user = userRepository.findById(petFindRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id "
                        + petFindRequest.getUserId()));
        Pet foundPet = user.retrievePetByName(petFindRequest.getName())
                .orElseThrow(() -> new PetNameNotFoundException("User with id " + petFindRequest.getUserId()
                        + " doesn't have a pet named " + petFindRequest.getName()));
        user.getPets().remove(foundPet);
        userRepository.save(user);
    }

    @Override
    public Pet getSinglePet(PetFindRequest petFindRequest) {
        User user = userRepository.findById(petFindRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id "
                        + petFindRequest.getUserId()));
        return user.retrievePetByName(petFindRequest.getName())
                .orElseThrow(() -> new PetNameNotFoundException("User with id " + petFindRequest.getUserId()
                        + " doesn't have a pet named " + petFindRequest.getName()));
    }

    @Override
    public List<Pet> getUserPets(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        return user.getPets();
    }

    @Override
    public List<Pet> getAllPets() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .flatMap(user -> user.getPets().stream())
                .toList();
    }

    @Override
    public Pet updatePet(PetUpdateRequest petUpdateRequest) {
        String userId = petUpdateRequest.getUserId();
        String petName = petUpdateRequest.getName();
        Set<Accessory> newAccessories = petUpdateRequest.getAccessories();
        Location newLocation = petUpdateRequest.getLocation();
        PetInteraction petInteraction = petUpdateRequest.getPetInteraction();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id "
                        + userId));
        Pet foundPet = user.retrievePetByName(petName)
                .orElseThrow(() -> new PetNameNotFoundException("User with id " + userId
                        + " doesn't have a pet named " + petName));

        if (!newAccessories.equals(foundPet.getAccessories())) {
            user.changePetAccessories(petName, newAccessories);
        } else if (!newLocation.equals(foundPet.getLocation())) {
            user.changePetLocation(petName, newLocation);
        } else user.interactWithPet(petName, petInteraction);

        userRepository.save(user);
        return foundPet;
    }

}
