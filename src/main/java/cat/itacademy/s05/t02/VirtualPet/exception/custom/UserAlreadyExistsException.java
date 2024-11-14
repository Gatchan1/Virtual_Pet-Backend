package cat.itacademy.s05.t02.VirtualPet.exception.custom;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
