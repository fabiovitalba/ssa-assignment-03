package gamification.examples;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.GamificationFacade;
import gamification.rules.RecordPoints;
import gamification.rules.TrueFalseReturn;
import gamification.user.User;
import gamification.user.UserRegistry;

public class MultipleRulesForATaskMain {
    public static void main(String[] args) {
        // Create and set a User as the current User
        User user = new User("example_username");
        UserRegistry.setCurrentUser(user);

        // Configure the Game Rules for the Application
        // rule1 awards 1 point for each Task execution
        GameRule rule1 = new RecordPoints(1);
        // rule2 awards 5 points for each Task that returns true, and removes 1 point for each Task that returns false.
        GameRule rule2 = new TrueFalseReturn(5,-1);

        // Apply the Game Rules to a Task-class
        // rule1 applies to any executions of the PrintTextTask.
        GamificationFacade.getInstance().setGameRule(rule1, ReturnTrueFalseTask.class);
        GamificationFacade.getInstance().setGameRule(rule2, ReturnTrueFalseTask.class);

        // Execute the ReturnTrueFalseTask
        try {
            for(int i = 0; i < 6; i++) {
                GamificationFacade.getInstance().execute(new ReturnTrueFalseTask(i < 4));
                System.out.println("User has now " + UserRegistry.getCurrentUser().getPoints() + " Point(s).");
            }
        } catch (FailedExecutionException ex) {
            System.out.println("Something went wrong.");
        }
    }
}
