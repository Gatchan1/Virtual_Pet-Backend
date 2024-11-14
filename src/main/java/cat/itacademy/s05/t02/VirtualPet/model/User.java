package cat.itacademy.s05.t02.VirtualPet.model;

import cat.itacademy.s05.t02.VirtualPet.enums.Accessory;
import cat.itacademy.s05.t02.VirtualPet.enums.Location;
import cat.itacademy.s05.t02.VirtualPet.enums.PetInteraction;
import cat.itacademy.s05.t02.VirtualPet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User implements UserDetails {
    @Id private String id;
    private String userName;
    private String email;
    private String hashedPassword;
    private Role role;
    private List<Pet> pets = new ArrayList<>();

    public Optional<Pet> retrievePetByName(String petName) {
        return pets.stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst();
    }

    public void changePetAccessories(String petName, Set<Accessory> accessories) {
        Pet pet = retrievePetByName(petName).get(); // checked not null in PetService method updatePet
        pet.changeAccessories(accessories);
    }

    public void changePetLocation(String petName, Location location) {
        Pet pet = retrievePetByName(petName).get(); // checked not null in PetService method updatePet
        pet.changeLocation(location);
    }

    public void interactWithPet(String petName, PetInteraction interaction) {
        Pet pet = retrievePetByName(petName).get(); // checked not null in PetService method updatePet
        pet.interact(interaction);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return this.hashedPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
