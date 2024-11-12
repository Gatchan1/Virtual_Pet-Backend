package cat.itacademy.s05.t02.VirtualPet.model.petPersonality;

import cat.itacademy.s05.t02.VirtualPet.enums.Accessory;
import cat.itacademy.s05.t02.VirtualPet.enums.TasteLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryPreference {
    private Accessory accessory;
    private TasteLevel tasteLevel;
}
