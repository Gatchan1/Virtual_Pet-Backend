package cat.itacademy.s05.t02.VirtualPet.model;

import cat.itacademy.s05.t02.VirtualPet.enums.PetType;
import cat.itacademy.s05.t02.VirtualPet.model.petPersonality.AccessoryPreference;
import cat.itacademy.s05.t02.VirtualPet.model.petPersonality.LocationPreference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private PetType type;
    private String name;
    private List<AccessoryPreference> accessoryPreferences;
    private List<LocationPreference> locationPreferences;
    private int happiness;
    private int energy;
}
