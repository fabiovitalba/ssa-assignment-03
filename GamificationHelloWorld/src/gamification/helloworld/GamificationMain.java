package gamification.helloworld;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.GamificationFacade;
import gamification.rules.RecordPoints;
import gamification.user.User;
import gamification.user.UserRegistry;

public class GamificationMain {
    public static void main(String[] args) {
        // Create and set a User as the current User
        User user = new User("example_username");
        UserRegistry.setCurrentUser(user);

        // Configure the Game Rules for the Application
        // rule1 awards 1 point for each Task execution
        GameRule rule1 = new RecordPoints(1);

        // Apply the Game Rules to a Task-class
        // rule1 applies to any executions of the PrintTextTask.
        GamificationFacade.getInstance().setGameRule(rule1,PrintTextTask.class);

        // Execute the PrintTextTask
        try {
            PrintTextTask task = new PrintTextTask("Hello World!");
            GamificationFacade.getInstance().execute(task);
        } catch (FailedExecutionException ex) {
            System.out.println("Something went wrong.");
        }

        System.out.println("User has now " + UserRegistry.getCurrentUser().getPoints() + " Point(s).");
    }
}
