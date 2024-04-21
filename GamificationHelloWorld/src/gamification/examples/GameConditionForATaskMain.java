package gamification.examples;

import gamification.api.*;
import gamification.conditions.PointsWon;
import gamification.listeners.ChangePoints;
import gamification.rules.RecordPoints;
import gamification.rules.TrueFalseReturn;
import gamification.user.User;
import gamification.user.UserRegistry;

public class GameConditionForATaskMain {
    public static void main(String[] args) {
        // Create and set a User as the current User
        User user = new User("example_username");
        UserRegistry.setCurrentUser(user);

        // Configure the Game Rules for the Application
        // Configure basic rule, which gives 5 points per Task execution
        GameRule rule = new RecordPoints(5);
        GamificationFacade.getInstance().setGameRule(rule, ReturnTrueFalseTask.class);

        // Configure Callback Condition
        // condition results in "true" only if the User just received at least 5 points
        // Note that PointsWon is not persistent, therefore it will be executed only once!
        GameCondition condition = new PointsWon(5);
        // listener adds 2 to the current User's points
        GamificationListener listener = new ChangePoints(2);
        // assign the listener to the GamificationFacade instance. The condition holds for all types of tasks.
        GamificationFacade.getInstance().setCallback(condition,listener);

        // Execute the ReturnTrueFalseTask
        try {
            for(int i = 0; i < 3; i++) {
                GamificationFacade.getInstance().execute(new ReturnTrueFalseTask(true));
                System.out.println("User has now " + UserRegistry.getCurrentUser().getPoints() + " Point(s).");
            }
        } catch (FailedExecutionException ex) {
            System.out.println("Something went wrong.");
        }
    }
}
