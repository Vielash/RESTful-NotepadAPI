package notepadingo.exception;

public class NoteServiceException extends RuntimeException {
    public NoteServiceException() {
        super();

    }

    public NoteServiceException(String message) {
        super(message);
    }

    public NoteServiceException(String message, Throwable cause) {
        super(message, cause);

    }



}
