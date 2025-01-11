package notepadingo.exception;

public class NoteAlreadyExistException extends NoteServiceException{

    public NoteAlreadyExistException(String message) {
        super(message);
    }
}
