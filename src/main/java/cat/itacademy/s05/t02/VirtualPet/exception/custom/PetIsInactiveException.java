package cat.itacademy.s05.t02.VirtualPet.exception.custom;

public class PetIsInactiveException extends RuntimeException {
    public PetIsInactiveException(String message) {
        super(message);
    }
}