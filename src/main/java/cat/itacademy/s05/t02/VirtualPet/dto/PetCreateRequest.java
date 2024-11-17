package cat.itacademy.s05.t02.VirtualPet.dto;

import cat.itacademy.s05.t02.VirtualPet.enums.PetColor;
import cat.itacademy.s05.t02.VirtualPet.enums.PetType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetCreateRequest {
    private String userId;
    @NotEmpty private String name;
    private PetType type;
    private PetColor color;
}
