package gamification.api;

public class FailedExecutionException extends Exception {

    public FailedExecutionException() {

    }

    public FailedExecutionException(String message) {
        super(message);
    }

    public FailedExecutionException(Throwable cause) {
        super(cause);
    }

    public FailedExecutionException(String message, Throwable cause) {
        super(message,cause);
    }

    public FailedExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
