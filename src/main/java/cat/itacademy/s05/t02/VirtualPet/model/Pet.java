package cat.itacademy.s05.t02.VirtualPet.model;

import cat.itacademy.s05.t02.VirtualPet.enums.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public void changeAccessories(Set<Accessory> newAccessories) {
        if (accessories.size() > newAccessories.size()) { //removed accessory
            Accessory addedAccessory = accessories.stream()
                    .filter(accessory -> !newAccessories.contains(accessory))
                    .findFirst().get();
            int happinessAdjustment = accessoryPreferences.get(addedAccessory).getHappinessAdjustment();
            if (happinessAdjustment > 0) increaseValue(happinessAdjustment, this::setHappiness, this::getHappiness);
            else decreaseValue(happinessAdjustment, this::setHappiness, this::getHappiness);

        } else if (accessories.size() < newAccessories.size()) { //added accessory
            Accessory removedAccessory = newAccessories.stream()
                    .filter(accessory -> !accessories.contains(accessory))
                    .findFirst().get();
            int happinessAdjustment = accessoryPreferences.get(removedAccessory).getHappinessAdjustment();
            if (happinessAdjustment > 0) decreaseValue(happinessAdjustment, this::setHappiness, this::getHappiness);
            else increaseValue(happinessAdjustment, this::setHappiness, this::getHappiness);
        }
        setAccessories(newAccessories);
        wakeUp();
    }

    public void changeLocation(Location newLocation) {
        if (!location.equals(newLocation)) {
            happiness -= locationPreferences.get(location).getHappinessAdjustment();
            happiness += locationPreferences.get(newLocation).getHappinessAdjustment();
            setLocation(newLocation);
        }
        wakeUp();
    }

    public void interact(PetInteraction interaction) {
        if (interaction == PetInteraction.EAT) {
            increaseValue(15, this::setEnergy, this::getEnergy);
        } else if (interaction == PetInteraction.PLAY) {
            increaseValue(15, this::setHappiness, this::getHappiness);
            decreaseValue(5, this::setEnergy, this::getEnergy);
        }
        wakeUp();
    }

    private void wakeUp() {
        isAsleep = false;
    }

    public void checkIfSleeping() {
        int currentHour = LocalDateTime.now().getHour();
        if (currentHour >= 22 || currentHour < 10) {
            isAsleep = true;
        } else isAsleep = (happiness > 0 && energy < 20);
    }

    public void hourlyBehaviourUpdate() {
        checkIfSleeping();
        if (isAsleep) {
            increaseValue(5, this::setEnergy, this::getEnergy);
        } else {
            decreaseValue(5, this::setHappiness, this::getHappiness);
            decreaseValue(5, this::setEnergy, this::getEnergy);
        }
        checkIfSleeping();
    }

    private void increaseValue(int value, Consumer<Integer> setter, Supplier<Integer> getter) {
        int newValue = Math.min(getter.get() + value, 100); // Increase up to 100
        setter.accept(newValue);
    }

    private void decreaseValue(int value, Consumer<Integer> setter, Supplier<Integer> getter) {
        int newValue = Math.max(getter.get() - value, 0); // Decrease down to 0
        setter.accept(newValue);
    }
}
