package gamification.rules;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.Task;
import gamification.user.User;
import gamification.user.UserRegistry;

public class RecordPoints implements GameRule {
    private int numberOfPoints;

    public RecordPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public void beforeExecution(Task task) {
        UserRegistry.getCurrentUser().changePointsBy(numberOfPoints);
    }

    @Override
    public void whenTaskReturns(Task task, Object returned) {
    }

    @Override
    public void whenTaskThrowsException(Task task, FailedExecutionException exception) {
    }
}
