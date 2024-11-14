package cat.itacademy.s05.t02.VirtualPet.dto;

import cat.itacademy.s05.t02.VirtualPet.enums.PetColor;
import cat.itacademy.s05.t02.VirtualPet.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetCreateRequest {
    private String userId;
    private String name;
    private PetType type;
    private PetColor color;
}
