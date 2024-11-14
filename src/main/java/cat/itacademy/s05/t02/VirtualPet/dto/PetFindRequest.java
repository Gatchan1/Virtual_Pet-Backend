package cat.itacademy.s05.t02.VirtualPet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetFindRequest {
    private String userId;
    private String name;
}
