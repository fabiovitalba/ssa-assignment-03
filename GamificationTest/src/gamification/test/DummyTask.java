package gamification.test;

import gamification.api.FailedExecutionException;
import gamification.api.Task;

public class DummyTask implements Task {
    private boolean error = false;

    public DummyTask() {
    }

    public DummyTask(boolean error) {
        this.error = error;
    }

    @Override
    public Object execute() throws FailedExecutionException {
        if (error)
            throw new FailedExecutionException("Test");
        return null;
    }
}
