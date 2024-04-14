package gamification.rules;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.Task;
import gamification.user.UserRegistry;

public class AnnoyingPerson implements GameRule {
    @Override
    public void beforeExecution(Task task) {

    }

    @Override
    public void whenTaskReturns(Task task, Object returned) {

    }

    @Override
    public void whenTaskThrowsException(Task task, FailedExecutionException exception) {
        UserRegistry.getCurrentUser().addBadge("Annoying");
    }
}
