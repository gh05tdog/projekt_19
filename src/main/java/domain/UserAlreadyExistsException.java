package domain;
//Lavet af Olvier
public class UserAlreadyExistsException extends Throwable {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
}
