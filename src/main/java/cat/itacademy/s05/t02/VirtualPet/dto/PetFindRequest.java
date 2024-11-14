package cat.itacademy.s05.t02.VirtualPet.dto;

import cat.itacademy.s05.t02.VirtualPet.enums.Accessory;
import cat.itacademy.s05.t02.VirtualPet.enums.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetFindRequest {
    private String userId;
    private String name;
    private int happiness;
    private int energy;
    private Set<Accessory> accessories;
    private Location location;
}
