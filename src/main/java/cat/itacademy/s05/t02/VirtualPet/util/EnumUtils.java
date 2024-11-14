package cat.itacademy.s05.t02.VirtualPet.util;

import java.util.Random;

public class EnumUtils {
    private static final Random random = new Random();

    public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[random.nextInt(values.length)];
    }
}
