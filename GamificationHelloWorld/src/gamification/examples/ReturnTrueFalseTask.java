package gamification.examples;

import gamification.api.FailedExecutionException;
import gamification.api.Task;

public class ReturnTrueFalseTask implements Task {
    private boolean whatIWantToReturn;
    public ReturnTrueFalseTask(boolean whatIWantToReturn) {
        this.whatIWantToReturn = whatIWantToReturn;
    }

    @Override
    public Object execute() throws FailedExecutionException {
        // this primitive boolean is automatically wrapped in
        // the Boolean-class.
        return whatIWantToReturn;
    }
}
