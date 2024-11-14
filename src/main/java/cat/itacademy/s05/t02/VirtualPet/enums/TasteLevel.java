package cat.itacademy.s05.t02.VirtualPet.enums;

import lombok.Getter;

@Getter
public enum TasteLevel {
    LIKE_STRONGLY(10),
    LIKE(5),
    DISLIKE(-5),
    DISLIKE_STRONGLY(-10);

    private final int happinessAdjustment;

    TasteLevel(int happinessAdjustment) {
        this.happinessAdjustment = happinessAdjustment;
    }
}
