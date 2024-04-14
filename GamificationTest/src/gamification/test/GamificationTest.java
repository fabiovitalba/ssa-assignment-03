package gamification.test;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.GamificationFacade;
import gamification.rules.RecordPoints;
import gamification.user.User;

import gamification.user.UserRegistry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamificationTest {
    @Test
    void basicFrameworkUsage() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new RecordPoints(10);
        // We assign the Game Rule to DummyTask.class
        // In the setGameRule class, we specified that we can only assign rules to Task-implementing classes
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTask.class);

        // Execute the task
        GamificationFacade.getInstance().execute(new DummyTask());
        GamificationFacade.getInstance().execute(new DummyTask());

        // Verify the points
        assertEquals(20, user.getPoints());
        assertEquals(10, user.getLastPointsGot());
    }
}
