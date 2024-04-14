package gamification.api;

public interface GameRule {

    public void beforeExecution(Task task);

    public void whenTaskReturns(Task task, Object returned);

    public void whenTaskThrowsException(Task task, FailedExecutionException exception);
}
