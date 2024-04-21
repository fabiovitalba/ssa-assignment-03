package gamification.api;

public interface GameRule {

    void beforeExecution(Task task);

    void whenTaskReturns(Task task, Object returned);

    void whenTaskThrowsException(Task task, FailedExecutionException exception);
}
