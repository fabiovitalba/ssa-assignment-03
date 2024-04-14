package gamification.api;

public interface Task {
    Object execute() throws FailedExecutionException;
}
