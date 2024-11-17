package cat.itacademy.s05.t02.VirtualPet.dto;

import cat.itacademy.s05.t02.VirtualPet.enums.*;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetWithUserInfo {
    private String userId;
    private String userName;
    private PetType type;
    private String name;
    private PetColor color;
    private Map<Accessory, TasteLevel> accessoryPreferences;
    private Map<Location, TasteLevel> locationPreferences;
    private int happiness;
    private int energy;
    private boolean isAsleep;
    private Set<Accessory> accessories;
    private Location location;
}
