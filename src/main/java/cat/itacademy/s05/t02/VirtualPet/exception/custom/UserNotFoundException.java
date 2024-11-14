package cat.itacademy.s05.t02.VirtualPet.exception.custom;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
