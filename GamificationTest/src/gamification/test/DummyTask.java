package gamification.test;

import gamification.api.FailedExecutionException;
import gamification.api.Task;

public class DummyTask implements Task {
    @Override
    public Object execute() throws FailedExecutionException {
        return null;
    }
}
