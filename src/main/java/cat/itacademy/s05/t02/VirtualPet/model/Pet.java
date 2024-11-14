package cat.itacademy.s05.t02.VirtualPet.model;

import cat.itacademy.s05.t02.VirtualPet.enums.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private PetType type;
    @NonNull private String name;
    private PetColor color;
    private Map<Accessory, TasteLevel> accessoryPreferences;
    private Map<Location, TasteLevel> locationPreferences;
    private int happiness;
    private int energy;
    private boolean isAsleep;
    private Set<Accessory> accessories;
    private Location location;
    private LocalDateTime lastUpdated;

    public void changeAccessories(Set<Accessory> newAccessories) {
        if (accessories.size() > newAccessories.size()) { //removed accessory
            Accessory addedAccessory = accessories.stream()
                    .filter(accessory -> !newAccessories.contains(accessory))
                    .findFirst().get();
            happiness += accessoryPreferences.get(addedAccessory).getHappinessAdjustment();
        } else if (accessories.size() < newAccessories.size()) { //added accessory
            Accessory removedAccessory = newAccessories.stream()
                    .filter(accessory -> !accessories.contains(accessory))
                    .findFirst().get();
            happiness -= accessoryPreferences.get(removedAccessory).getHappinessAdjustment();
        }
        setAccessories(newAccessories);
        wakeUp();
        lastUpdated = LocalDateTime.now();
    }

    public void changeLocation(Location newLocation) {
        if (!location.equals(newLocation)) {
            happiness -= locationPreferences.get(location).getHappinessAdjustment();
            happiness += locationPreferences.get(newLocation).getHappinessAdjustment();
            setLocation(newLocation);
        }
        wakeUp();
        lastUpdated = LocalDateTime.now();
    }

    public void interact(PetInteraction interaction) {
        if (interaction == PetInteraction.EAT) {
            energy += 10;
        } else if (interaction == PetInteraction.PLAY) {
            happiness += 10;
            energy -= 5;
        }
        wakeUp();
        lastUpdated = LocalDateTime.now();
    }

    private void wakeUp() {
        isAsleep = false;
    }
}
