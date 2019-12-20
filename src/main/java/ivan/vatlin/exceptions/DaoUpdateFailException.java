package ivan.vatlin.exceptions;

public class DaoUpdateFailException extends Exception {
    public DaoUpdateFailException() {
    }

    public DaoUpdateFailException(String message) {
        super(message);
    }
}
