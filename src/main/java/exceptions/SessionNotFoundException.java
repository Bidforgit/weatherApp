package exceptions;

import lombok.Getter;

@Getter
public class SessionNotFoundException  extends RuntimeException {
    private final String id;


    public SessionNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }
    public SessionNotFoundException(String id) {
        this.id = id;
    }
}