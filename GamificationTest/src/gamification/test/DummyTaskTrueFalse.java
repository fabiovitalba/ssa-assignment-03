package gamification.test;

import gamification.api.FailedExecutionException;
import gamification.api.Task;

public class DummyTaskTrueFalse implements Task {
    private boolean whatIWantToReturn;
    public DummyTaskTrueFalse(boolean whatIWantToReturn) {
        this.whatIWantToReturn = whatIWantToReturn;
    }

    @Override
    public Object execute() throws FailedExecutionException {
        // this primitive boolean is automatically wrapped in
        // the Boolean-class.
        return whatIWantToReturn;
    }
}
