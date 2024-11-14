package cat.itacademy.s05.t02.VirtualPet.exception.custom;

public class PetNameAlreadyExistsException extends RuntimeException {
    public PetNameAlreadyExistsException(String message) {
        super(message);
    }
}
