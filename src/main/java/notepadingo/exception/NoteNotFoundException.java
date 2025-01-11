package notepadingo.exception;

public class NoteNotFoundException extends NoteServiceException {


    public NoteNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public NoteNotFoundException(String exceptionMessage, Throwable chainCause) { //I learned this new for example there is 2 exceptions named A and B. If A is thrown
        super(exceptionMessage, chainCause);                                 //and caused to program throw B exception you write A exception object in B's constructor.
    }

}
