package hello.jdbc.repository.ex;

public class MyDuplicatedKeyException extends MyDbException{

    public MyDuplicatedKeyException() {
    }

    public MyDuplicatedKeyException(String message) {
        super(message);
    }

    public MyDuplicatedKeyException(Throwable cause) {
        super(cause);
    }

    public MyDuplicatedKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicatedKeyException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
