package gamification.rules;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.Task;
import gamification.user.UserRegistry;

public class TrueFalseReturn implements GameRule {
    private int pointsIfTrue;
    private int pointsIfFalse;

    public TrueFalseReturn(int pointsIfTrue, int pointsIfFalse) {
        this.pointsIfTrue = pointsIfTrue;
        this.pointsIfFalse = pointsIfFalse;
    }

    @Override
    public void beforeExecution(Task task) {

    }

    @Override
    public void whenTaskReturns(Task task, Object returned) {
        if ((Boolean)returned) {
            UserRegistry.getCurrentUser().changePointsBy(pointsIfTrue);
        } else {
            UserRegistry.getCurrentUser().changePointsBy(pointsIfFalse);
        }
    }

    @Override
    public void whenTaskThrowsException(Task task, FailedExecutionException exception) {

    }
}
