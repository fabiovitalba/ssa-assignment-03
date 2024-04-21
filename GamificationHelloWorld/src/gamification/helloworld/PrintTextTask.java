package gamification.helloworld;

import gamification.api.FailedExecutionException;
import gamification.api.Task;

public class PrintTextTask implements Task {
    private String textToPrint;

    public PrintTextTask(String textToPrint) {
        this.textToPrint = textToPrint;
    }

    @Override
    public Object execute() throws FailedExecutionException {
        System.out.println(textToPrint);
        return null;
    }
}
