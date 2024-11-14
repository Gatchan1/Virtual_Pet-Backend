package cat.itacademy.s05.t02.VirtualPet.exception.custom;

public class PetNameNotFoundException extends RuntimeException {
    public PetNameNotFoundException(String message) {
        super(message);
    }
}
